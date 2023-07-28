package spring.blog.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.bl.dto.PostDto;
import spring.blog.bl.services.posts.PostService;
import spring.blog.web.form.PostForm;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;

	@RequestMapping("/posts/create")
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("postCreateView");
		mv.addObject("postForm", new PostForm());
		return mv;
	}
	
	@RequestMapping(value = "/posts/store", method = RequestMethod.POST)
	public ModelAndView store(@ModelAttribute("postForm") @Valid PostForm postForm,
			BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("postCreateView");
			return mv;
		}
		
		this.postService.savePost(postForm);
		mv.setViewName("redirect:/posts");
		return mv;
	}
	
	@RequestMapping("/posts")
	public ModelAndView index() {
		List<PostDto> posts = this.postService.getAllPosts();
		ModelAndView mv = new ModelAndView("postListView");
		mv.addObject("posts", posts);
		return mv;	
	}
	
	@RequestMapping("/posts/edit")
	public ModelAndView edit(@RequestParam("id") Long id) {
		PostDto postDto = this.postService.getPostById(id);
		ModelAndView mv = new ModelAndView("postEditView");
		mv.addObject("postForm", new PostForm(postDto));
		return mv;
	}
	
	@RequestMapping(value = "/posts/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mv.setViewName("postEditView");
			return mv;
		}
		
		this.postService.updatePost(postForm);
		mv.setViewName("redirect:/posts");	
		return mv;
	}
	
	@RequestMapping("/posts/destroy")
	public String destroy(@RequestParam("id") Long id) {
		this.postService.deletePostById(id);
		
		return "redirect:/posts";
	}
}
