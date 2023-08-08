package spring.blog.bl.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spring.blog.persistence.entity.Role;
import spring.blog.persistence.entity.User;

/**
 * <h2>UserDto Class</h2>
 * <p>
 * Process for Displaying UserDto
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserDto {

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
    private String name;

    /**
     * <h2>email</h2>
     * <p>
     * email
     * </p>
     */
    private String email;

    /**
     * <h2>roles</h2>
     * <p>
     * roles
     * </p>
     */
    private List<Role> roles;

    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    private String password;

    /**
     * <h2>created_at</h2>
     * <p>
     * created_at
     * </p>
     */
    private Date created_at;

    /**
     * <h2>Constructor for UserDto</h2>
     * <p>
     * Constructor for UserDto
     * </p>
     */
    public UserDto() {
        super();
    }

    /**
     * <h2>Constructor for UserDto</h2>
     * <p>
     * Constructor for UserDto
     * </p>
     * 
     * @param user
     */
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.password = user.getPassword();
        this.created_at = user.getCreated_at();
    }
}
