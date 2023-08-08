package spring.blog.bl.services.resetPassword;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import spring.blog.persistence.entity.ResetPassword;
import spring.blog.web.form.ForgotPasswordForm;
import spring.blog.web.form.ResetPasswordForm;

public interface ResetPasswordService {

    public ResetPassword findBytoken(String token);

    public void sendResetMail(ForgotPasswordForm forgotPasswordForm, HttpServletRequest request);

    public boolean isEqualPwdWithConfirmPwd(@Valid ResetPasswordForm resetPasswordForm);

    public void updatePassword(@Valid ResetPasswordForm resetPasswordForm);
}
