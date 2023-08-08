package spring.blog.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>ResetPassword Class</h2>
 * <p>
 * Process for Displaying ResetPassword
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Entity
@Table(name = "reset_passwords")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {

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
     * <h2>token</h2>
     * <p>
     * token
     * </p>
     */
    private String token;

    /**
     * <h2>user</h2>
     * <p>
     * user
     * </p>
     */
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * <h2>expiryDate</h2>
     * <p>
     * expiryDate
     * </p>
     */
    @Column(name = "expiry_at")
    private LocalDateTime expiryDate;
}