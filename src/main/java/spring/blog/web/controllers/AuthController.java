package spring.blog.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.bl.dto.RoleDto;
import spring.blog.bl.services.roles.RoleService;
import spring.blog.bl.services.users.UserService;
import spring.blog.web.form.LoginForm;
import spring.blog.web.form.UserForm;

/**
 * <h2>AuthController Class</h2>
 * <p>
 * Process for Displaying AuthController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class AuthController {

    /**
     * <h2>roleService</h2>
     * <p>
     * roleService
     * </p>
     */
    @Autowired
    private RoleService roleService;

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * <h2>register</h2>
     * <p>
     * register user
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        List<RoleDto> roles = this.roleService.getRoles();
        ModelAndView mv = new ModelAndView("registerFormView");
        mv.addObject("roles", roles);
        mv.addObject("registerForm", new UserForm());
        return mv;
    }

    /**
     * <h2>store</h2>
     * <p>
     * store user
     * </p>
     *
     * @param registerForm
     * @param bindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/users/store", method = RequestMethod.POST)
    public ModelAndView store(@ModelAttribute("registerForm") @Valid UserForm registerForm,
            BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            List<RoleDto> roles = this.roleService.getRoles();
            mv.addObject("roles", roles);
            mv.setViewName("registerFormView");
            return mv;
        }
        if (!this.userService.isEqualPwdWithConfirmPwd(registerForm)) {
            List<RoleDto> roles = this.roleService.getRoles();
            mv.addObject("roles", roles);
            mv.addObject("errorMessage", "Password must be the same with Confirm Password.");
            mv.setViewName("registerFormView");
            return mv;
        }
        registerForm.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        this.userService.saveUser(registerForm);
        mv.setViewName("redirect:/posts");
        return mv;
    }

    /**
     * <h2>login</h2>
     * <p>
     * login
     * </p>
     *
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("loginFormView");
        if (this.userService.doIsLoggedIn()) {
            mv.setViewName("redirect:/home");
            return mv;
        }
        mv.addObject("loginForm", new LoginForm());
        return mv;
    }
}