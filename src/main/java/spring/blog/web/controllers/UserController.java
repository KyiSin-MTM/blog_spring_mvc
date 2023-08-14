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

import spring.blog.bl.dto.UserDto;
import spring.blog.bl.services.users.UserService;
import spring.blog.persistence.entity.User;
import spring.blog.web.form.UserEditForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Process for Displaying UserController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class UserController {

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>index</h2>
     * <p>
     * user list
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/users")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("userListView");
        List<UserDto> users = this.userService.getAllUsers();
        mv.addObject("users", users);
        return mv;
    }

    /**
     * <h2>edit</h2>
     * <p>
     * user edit
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/users/edit")
    public ModelAndView edit() {
        ModelAndView mv = new ModelAndView("userEditView");
        User user = this.userService.doGetLoginInfo();
        UserEditForm form = new UserEditForm(user);
        mv.addObject("userEditForm", form);
        return mv;
    }

    /**
     * <h2>update</h2>
     * <p>
     * user update
     * </p>
     *
     * @param userEditForm
     * @param bindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
            BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("userEditView");
            return mv;
        }
        User user = this.userService.doGetLoginInfo();
        userEditForm.setId(user.getId());
        this.userService.updateUser(userEditForm);
        mv.setViewName("redirect:/profile");
        return mv;
    }

    /**
     * <h2>destroy</h2>
     * <p>
     * delete user by id
     * </p>
     *
     * @param id
     * @return
     * @return String
     */
    @RequestMapping("/users/destroy")
    public String destroy(@RequestParam("id") Long id) {
        this.userService.deleteUserById(id);
        return "redirect:/users";
    }

    /**
     * <h2>search</h2>
     * <p>
     * user search
     * </p>
     *
     * @param searchKey
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/users/search")
    public ModelAndView search(@RequestParam("searchKey") String searchKey) {
        List<UserDto> users = this.userService.getSearchUsers(searchKey);
        ModelAndView mv = new ModelAndView("userListView");
        mv.addObject("users", users);
        mv.addObject("searchKey", searchKey);
        return mv;
    }
}
