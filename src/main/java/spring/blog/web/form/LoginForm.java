package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.User;

/**
 * <h2>LoginForm Class</h2>
 * <p>
 * Process for Displaying LoginForm
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    
    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    @NotBlank(message = "Email field is required.")
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
     * <h2>Constructor for LoginForm</h2>
     * <p>
     * Constructor for LoginForm
     * </p>
     * 
     * @param user
     */
    public LoginForm(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
