package spring.blog.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.bl.services.resetPassword.ResetPasswordService;
import spring.blog.persistence.entity.ResetPassword;
import spring.blog.web.form.ResetPasswordForm;

/**
 * <h2>ResetPasswordController Class</h2>
 * <p>
 * Process for Displaying ResetPasswordController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class ResetPasswordController {

    /**
     * <h2>resetPasswordService</h2>
     * <p>
     * resetPasswordService
     * </p>
     */
    @Autowired
    private ResetPasswordService resetPasswordService;

    /**
     * <h2>resetPage</h2>
     * <p>
     * reset password form
     * </p>
     *
     * @param token
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/reset_password")
    public ModelAndView resetPage(@RequestParam("token") String token) {
        ResetPassword resetPassword = this.resetPasswordService.findBytoken(token);
        ModelAndView mv = new ModelAndView();
        if (resetPassword == null) {
            mv.setViewName("invalidToken");
            return mv;
        }
        mv.addObject("resetPassword", resetPassword);
        mv.addObject("resetPasswordForm", new ResetPasswordForm());
        mv.setViewName("resetPassword");
        return mv;
    }

    /**
     * <h2>updatePassword</h2>
     * <p>
     * update password
     * </p>
     *
     * @param resetPasswordForm
     * @param bindingResult
     * @return
     * @return ModelAndView
     */
    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public ModelAndView updatePassword(@ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
            BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("resetPassword");
            return mv;
        }
        if (!this.resetPasswordService.isEqualPwdWithConfirmPwd(resetPasswordForm)) {
            mv.addObject("errorMessage", "Password must be the same with Confirm Password.");
            mv.setViewName("resetPassword");
            return mv;
        }
        this.resetPasswordService.updatePassword(resetPasswordForm);
        mv.addObject("successMessage", "Password has been reset.");
        mv.setViewName("resetPassword");
        return mv;
    }
}
