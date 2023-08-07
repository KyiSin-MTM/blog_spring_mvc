package spring.blog.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.bl.dto.UserDto;
import spring.blog.bl.services.users.UserService;

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
     * 
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
}
