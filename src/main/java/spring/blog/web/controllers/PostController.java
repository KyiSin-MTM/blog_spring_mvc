package spring.blog.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
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
import spring.blog.persistence.entity.Post;
import spring.blog.persistence.entity.User;
import spring.blog.web.form.PostForm;

/**
 * <h2>PostController Class</h2>
 * <p>
 * Process for Displaying PostController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class PostController {

    /**
     * <h2>postService</h2>
     * <p>
     * postService
     * </p>
     */
    @Autowired
    private PostService postService;

    /**
     * <h2>create</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/posts/create")
    public ModelAndView create() {
        ModelAndView mv = new ModelAndView("postCreateView");
        mv.addObject("postForm", new PostForm());
        return mv;
    }

    /**
     * <h2>store</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param bindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/posts/store", method = RequestMethod.POST)
    public ModelAndView store(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("postCreateView");
            return mv;
        }
        this.postService.savePost(postForm);
        mv.setViewName("redirect:/posts");
        return mv;
    }

    /**
     * <h2>index</h2>
     * <p>
     * 
     * </p>
     *
     * @param session
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/posts")
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute("loginedUser");
        System.out.println("user" + user);
        List<PostDto> posts = this.postService.getAllPosts();
        ModelAndView mv = new ModelAndView("postListView");
        mv.addObject("posts", posts);
        return mv;
    }

    /**
     * <h2>edit</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/posts/edit")
    public ModelAndView edit(@RequestParam("id") Long id) {
        PostDto postDto = this.postService.getPostById(id);
        ModelAndView mv = new ModelAndView("postEditView");
        mv.addObject("postForm", new PostForm(postDto));
        return mv;
    }

    /**
     * <h2>update</h2>
     * <p>
     * 
     * </p>
     *
     * @param postForm
     * @param bindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/posts/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("postForm") @Valid PostForm postForm, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("postEditView");
            return mv;
        }
        this.postService.updatePost(postForm);
        mv.setViewName("redirect:/posts");
        return mv;
    }

    /**
     * <h2>destroy</h2>
     * <p>
     * 
     * </p>
     *
     * @param id
     * @return
     * @return String
     */
    @RequestMapping("/posts/destroy")
    public String destroy(@RequestParam("id") Long id) {
        this.postService.deletePostById(id);
        return "redirect:/posts";
    }

    /**
     * <h2>search</h2>
     * <p>
     * 
     * </p>
     *
     * @param searchKey
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/posts/search")
    public ModelAndView search(@RequestParam("searchKey") String searchKey) {
        List<PostDto> posts = this.postService.getSearchPosts(searchKey);
        ModelAndView mv = new ModelAndView("postListView");
        mv.addObject("posts", posts);
        mv.addObject("searchKey", searchKey);
        return mv;
    }

    /**
     * <h2>excelExport</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/posts/excel/export")
    public ModelAndView excelExport() {
        List<PostDto> postDtoList = this.postService.getAllPosts();
        List<Post> posts = postDtoList.stream().map(post -> {
            Post postModel = new Post(post);
            return postModel;
        }).collect(Collectors.toList());
        return new ModelAndView("postExcelView", "posts", posts);
    }
}