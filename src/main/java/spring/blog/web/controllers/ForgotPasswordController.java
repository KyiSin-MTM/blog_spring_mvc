package spring.blog.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.bl.services.resetPassword.ResetPasswordService;
import spring.blog.bl.services.users.UserService;
import spring.blog.web.form.ForgotPasswordForm;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResetPasswordService resetPasswordService;
	
	@RequestMapping("/forgot_password")
	public ModelAndView forgotPassword() {
		ModelAndView mv = new ModelAndView("forgotPassword");
		ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
		mv.addObject("forgotPasswordForm", forgotPasswordForm);
		return mv;
	}
	
	@RequestMapping(value = "/email/check", method= RequestMethod.POST)
	public ModelAndView emailCheck(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm, 
			BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mv.setViewName("forgotPassword");
			return mv;
		} 
		if(!this.userService.checkedEmail(forgotPasswordForm.getEmail())) {
			mv.addObject("errorMessage", "Invalid Email");
			mv.setViewName("forgotPassword");
			return mv;
		}
		this.resetPasswordService.sendResetMail(forgotPasswordForm);
		mv.setViewName("redirect:/login");
		return mv;
	}

}
