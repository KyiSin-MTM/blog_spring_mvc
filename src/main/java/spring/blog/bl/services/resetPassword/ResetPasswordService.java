package spring.blog.bl.services.resetPassword;

import spring.blog.web.form.ForgotPasswordForm;

public interface ResetPasswordService {

	void sendResetMail(ForgotPasswordForm forgotPasswordForm);

}
