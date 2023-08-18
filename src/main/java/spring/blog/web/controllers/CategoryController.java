package spring.blog.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.bl.dto.CategoryDto;
import spring.blog.bl.services.categories.CategoryService;
import spring.blog.persistence.entity.Category;
import spring.blog.web.form.CategoryForm;

@Controller
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/categories/create")
    public String create(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "categoryCreateView";
    }
    
    @RequestMapping(value = "/categories/store", method = RequestMethod.POST)
    public ModelAndView store(@ModelAttribute("categoryForm") @Valid CategoryForm categoryForm, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if(bindingResult.hasErrors() ) {
            mv.setViewName("categoryCreateView");
        }
        this.categoryService.dbSaveCategory(categoryForm);
        mv.setViewName("redirect:/categories");
        return mv;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping("/categories")
    public ModelAndView index(HttpServletRequest request, ModelMap modelMap) {
        ModelAndView mv = new ModelAndView("categoryListView");
        List<CategoryDto> categories = this.categoryService.getAllcategories();
        PagedListHolder pagedListHolder = new PagedListHolder(categories);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        return mv;        
    }
    
    @RequestMapping("/categories/edit")
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView mv = new ModelAndView("categoryEditView");
        Category category = this.categoryService.getCategoryById(id);
        mv.addObject("categoryForm", new CategoryForm(category));
        return mv;
    }

    @RequestMapping(value="/categories/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("categoryForm") @Valid CategoryForm categoryForm, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if(bindingResult.hasErrors()) {
            mv.setViewName("categoryEditView");
            return mv;
        }
        this.categoryService.doUpdateCategory(categoryForm);
        mv.setViewName("redirect:/categories");
        return mv;
    }
    
    @RequestMapping("/categories/destroy")
    public String delete(@RequestParam("id") Long id) {
        this.categoryService.doDeleteCategory(id);
        return "redirect:/categories";
    }
}
