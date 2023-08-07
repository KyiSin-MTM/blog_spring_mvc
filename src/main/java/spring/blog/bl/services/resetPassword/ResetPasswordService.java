package spring.blog.bl.services.resetPassword;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import spring.blog.persistence.entity.ResetPassword;
import spring.blog.web.form.ForgotPasswordForm;
import spring.blog.web.form.ResetPasswordForm;

public interface ResetPasswordService {

    ResetPassword findBytoken(String token);

    void sendResetMail(ForgotPasswordForm forgotPasswordForm, HttpServletRequest request);

    boolean isEqualPwdWithConfirmPwd(@Valid ResetPasswordForm resetPasswordForm);

    void updatePassword(@Valid ResetPasswordForm resetPasswordForm);
}
