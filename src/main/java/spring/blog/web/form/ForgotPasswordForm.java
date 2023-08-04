package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2> ForgotPasswordForm Class</h2>
 * <p>
 * Process for Displaying ForgotPasswordForm
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordForm {
	
	/**
	 * <h2> email</h2>
	 * <p>
	 * email
	 * </p>
	 */
	@NotBlank(message = "Email field is required.")
	private String email;
}
