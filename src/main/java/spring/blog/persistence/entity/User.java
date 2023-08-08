package spring.blog.persistence.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.bl.dto.UserDto;
import spring.blog.web.form.RegisterForm;

/**
 * <h2>User Class</h2>
 * <p>
 * Process for Displaying User
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<Role>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ResetPassword resetPassword;

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
    @CreationTimestamp
    private Timestamp created_at;

    /**
     * <h2>updated_at</h2>
     * <p>
     * updated_at
     * </p>
     */
    @UpdateTimestamp
    private Timestamp updated_at;

    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param userForm
     */
    public User(RegisterForm userForm) {
        this.name = userForm.getName();
        this.email = userForm.getEmail();
        this.roles = userForm.getRoles();
        this.password = userForm.getPassword();
    }

    /**
     * <h2>Constructor for User</h2>
     * <p>
     * Constructor for User
     * </p>
     * 
     * @param userDto
     */
    public User(UserDto userDto) {
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.roles = userDto.getRoles();
        this.password = userDto.getPassword();
    }
}
