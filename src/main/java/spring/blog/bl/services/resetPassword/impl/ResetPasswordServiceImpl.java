package spring.blog.bl.services.resetPassword.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import spring.blog.bl.services.resetPassword.ResetPasswordService;
import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.User;
import spring.blog.web.form.ForgotPasswordForm;

public class ResetPasswordServiceImpl implements ResetPasswordService {
	
	@Autowired
	private UserDao userDao;
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void sendResetMail(ForgotPasswordForm forgotPasswordForm) {
		// TODO Auto-generated method stub
		User user = this.userDao.dbFindByEmail(forgotPasswordForm.getEmail());
		
	}

}
