package spring.blog.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	private JavaMailSender mailSender;

}
