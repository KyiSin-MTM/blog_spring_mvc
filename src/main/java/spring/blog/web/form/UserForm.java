package spring.blog.web.form;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Role;
import spring.blog.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private Long id;

    /**
     * <h2>name</h2>
     * <p>
     * name
     * </p>
     */
    @NotBlank(message = "Name field is required")
    private String name;

    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    @NotBlank(message = "Email field is required")
    @Email(message = "Email format is invalid")
    private String email;

    /**
     * <h2>roles</h2>
     * <p>
     * roles
     * </p>
     */
    private List<Role> roles;

    /**
     * <h2>role</h2>
     * <p>
     * role
     * </p>
     */
    private Role role;

    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    @NotBlank(message = "Password field is required")
    private String password;

    /**
     * <h2>confirmPassword</h2>
     * <p>
     * confirmPassword
     * </p>
     */
    @NotBlank(message = "Confirm password field is required")
    private String confirmPassword;

    /**
     * <h2>Constructor for RegisterForm</h2>
     * <p>
     * Constructor for RegisterForm
     * </p>
     * 
     * @param user
     */
    public UserForm(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.password = user.getPassword();
    }
}
