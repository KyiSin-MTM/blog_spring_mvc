package spring.blog.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import spring.blog.web.form.RegisterForm;

@Controller
public class AuthController {
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		List<RoleDto> roles = this.roleService.getRoles();
		ModelAndView mv = new ModelAndView("registerFormView");
		mv.addObject("roles", roles);
		mv.addObject("registerForm", new RegisterForm());
		return mv;
	}

	@RequestMapping(value = "/users/store", method = RequestMethod.POST)
	public ModelAndView store(@ModelAttribute("registerForm") @Valid RegisterForm registerForm,
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("loginFormView");
		if(this.userService.doIsLoggedIn()) {
		    mv.setViewName("postListView");
		}
		mv.addObject("loginForm", new LoginForm());
		return mv;
	}

	@RequestMapping(value = "/check/login", method = RequestMethod.POST)
	public ModelAndView checkLogin(@ModelAttribute("loginForm") @Valid LoginForm loginForm,
			BindingResult bindingResult, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mv.setViewName("loginFormView");
			return mv;
		}
		if (!this.userService.checkedLogin(loginForm, session)) {
			mv.addObject("errorMessage", "Invaid email or password.");
			mv.setViewName("loginFormView");
			return mv;
		}
		mv.setViewName("redirect:/posts");
		return mv;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("loginedUser");
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
}