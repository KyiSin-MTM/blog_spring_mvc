package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>ResetPasswordForm Class</h2>
 * <p>
 * Process for Displaying ResetPasswordForm
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordForm {

    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    private String email;

    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    @NotBlank(message = "Password field is required.")
    private String password;

    /**
     * <h2>confirmPassword</h2>
     * <p>
     * confirmPassword
     * </p>
     */
    @NotBlank(message = "Confirm Password field is required")
    private String confirmPassword;
}
