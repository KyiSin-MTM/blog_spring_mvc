package spring.blog.web.controllers;

import javax.servlet.http.HttpServletRequest;
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

/**
 * <h2>ForgotPasswordController Class</h2>
 * <p>
 * Process for Displaying ForgotPasswordController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class ForgotPasswordController {

    /**
     * <h2>userService</h2>
     * <p>
     * userService
     * </p>
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>resetPasswordService</h2>
     * <p>
     * resetPasswordService
     * </p>
     */
    @Autowired
    private ResetPasswordService resetPasswordService;

    /**
     * <h2>forgotPassword</h2>
     * <p>
     * forgot password form
     * </p>
     *
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
    public ModelAndView forgotPassword() {
        ModelAndView mv = new ModelAndView("forgotPassword");
        ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm();
        mv.addObject("forgotPasswordForm", forgotPasswordForm);
        return mv;
    }

    /**
     * <h2>emailCheck</h2>
     * <p>
     * checking
     * </p>
     *
     * @param forgotPasswordForm
     * @param bindingResult
     * @param request
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/email/check", method = RequestMethod.POST)
    public ModelAndView emailCheck(@ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
            BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("forgotPassword");
            return mv;
        }
        if (!this.userService.checkedEmail(forgotPasswordForm.getEmail())) {
            mv.addObject("errorMessage", "Invalid Email");
            mv.setViewName("forgotPassword");
            return mv;
        }
        this.resetPasswordService.sendResetMail(forgotPasswordForm, request);
        mv.addObject("successMessage", "We have sent a link to your mail box to reset password.");
        mv.setViewName("forgotPassword");
        return mv;
    }
}
