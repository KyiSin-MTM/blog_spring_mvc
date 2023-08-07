package spring.blog.bl.services.resetPassword.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.blog.bl.services.resetPassword.ResetPasswordService;
import spring.blog.persistence.dao.resetPassword.ResetPasswordDao;
import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.ResetPassword;
import spring.blog.persistence.entity.User;
import spring.blog.utils.EmailUtils;
import spring.blog.web.form.ForgotPasswordForm;
import spring.blog.web.form.ResetPasswordForm;

/**
 * <h2>ResetPasswordServiceImpl Class</h2>
 * <p>
 * Process for Displaying ResetPasswordServiceImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    /**
     * <h2>EXPIRATION</h2>
     * <p>
     * EXPIRATION
     * </p>
     */
    private final int EXPIRATION = 60 * 24;

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>resetPasswordDao</h2>
     * <p>
     * resetPasswordDao
     * </p>
     */
    @Autowired
    private ResetPasswordDao resetPasswordDao;

    /**
     * <h2>emailUtils</h2>
     * <p>
     * emailUtils
     * </p>
     */
    @Autowired
    private EmailUtils emailUtils;

    /**
     * <h2>passwordEncoder</h2>
     * <p>
     * passwordEncoder
     * </p>
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * <h2>sendResetMail</h2>
     * <p>
     * 
     * </p>
     * 
     * @param forgotPasswordForm
     * @param request
     */
    @Override
    public void sendResetMail(ForgotPasswordForm forgotPasswordForm, HttpServletRequest request) {
        User user = this.userDao.dbFindByEmail(forgotPasswordForm.getEmail());
        String token = UUID.randomUUID().toString();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String resetPasswordUrl = "http://" + serverName + ":" + port + "/Blog/reset_password?token=" + token;
        String subject = "Password Reset";
        String body = "Dear " + user.getName() + "," + System.lineSeparator()
                + "To reset your password, please click the link below." + System.lineSeparator() + resetPasswordUrl
                + "\n\n With best regards,\nAdmin Team";
        try {
            this.emailUtils.sendMail(user.getEmail(), subject, body);
            ResetPassword resetPassword = new ResetPassword();
            resetPassword.setToken(token);
            resetPassword.setUser(user);
            resetPassword.setExpiryDate(calculateExpiryDateTime());
            if (user.getResetPassword() != null) {
                this.resetPasswordDao.update(resetPassword);
            } else {
                this.resetPasswordDao.save(resetPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <h2>calculateExpiryDateTime</h2>
     * <p>
     * 
     * </p>
     *
     * @return
     * @return LocalDateTime
     */
    private LocalDateTime calculateExpiryDateTime() {
        LocalDateTime now = LocalDateTime.now();
        now.plus(EXPIRATION, ChronoUnit.MINUTES);
        return now;
    }

    /**
     * <h2>findBytoken</h2>
     * <p>
     * 
     * </p>
     * 
     * @param token
     * @return
     */
    @Override
    public ResetPassword findBytoken(String token) {
        return this.resetPasswordDao.findByTokenDao(token);
    }

    /**
     * <h2>isEqualPwdWithConfirmPwd</h2>
     * <p>
     * 
     * </p>
     * 
     * @param resetPasswordForm
     * @return
     */
    @Override
    public boolean isEqualPwdWithConfirmPwd(@Valid ResetPasswordForm resetPasswordForm) {
        if (resetPasswordForm.getPassword().equals(resetPasswordForm.getConfirmPassword())) {
            return true;
        }
        return false;
    }

    /**
     * <h2>updatePassword</h2>
     * <p>
     * 
     * </p>
     * 
     * @param resetPasswordForm
     */
    @Override
    public void updatePassword(@Valid ResetPasswordForm resetPasswordForm) {
        User user = this.userDao.dbFindByEmail(resetPasswordForm.getEmail());
        user.setPassword(passwordEncoder.encode(resetPasswordForm.getPassword()));
        this.userDao.dbUpdate(user);
    }
}
