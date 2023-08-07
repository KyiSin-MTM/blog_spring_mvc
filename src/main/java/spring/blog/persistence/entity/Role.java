package spring.blog.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spring.blog.bl.dto.RoleDto;

/**
 * <h2>Role Class</h2>
 * <p>
 * Process for Displaying Role
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
public class Role {

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
    @Column(name = "name")
    private String name;

    /**
     * <h2>Constructor for Role</h2>
     * <p>
     * Constructor for Role
     * </p>
     */
    public Role() {
        super();
    }

    /**
     * <h2>Constructor for Role</h2>
     * <p>
     * Constructor for Role
     * </p>
     * 
     * @param roleDto
     */
    public Role(RoleDto roleDto) {
        this.id = roleDto.getId();
        this.name = roleDto.getName();
    }
}