package spring.blog.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * <h2>EmailUtils Class</h2>
 * <p>
 * Process for Displaying EmailUtils
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Component
public class EmailUtils {

    /**
     * <h2>mailSender</h2>
     * <p>
     * mailSender
     * </p>
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * <h2>LOGGER</h2>
     * <p>
     * LOGGER
     * </p>
     */
    private static final Logger LOGGER = LogManager.getLogger(EmailUtils.class);

    /**
     * <h2>sendMail</h2>
     * <p>
     * send mail
     * </p>
     *
     * @param email
     * @param subject
     * @param body
     * @throws MessagingException
     * @return void
     */
    public void sendMail(String email, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(body, true);
        this.mailSender.send(mimeMessage);
        LOGGER.info("Email sent to: " + email);
    }
}
