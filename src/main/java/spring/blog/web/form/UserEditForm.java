package spring.blog.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditForm {
    
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

    private MultipartFile photo;

    /**
     * <h2>Constructor for RegisterForm</h2>
     * <p>
     * Constructor for RegisterForm
     * </p>
     * 
     * @param user
     */
    public UserEditForm(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
