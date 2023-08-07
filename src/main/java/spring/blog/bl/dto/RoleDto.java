package spring.blog.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Role;

/**
 * <h2>RoleDto Class</h2>
 * <p>
 * Process for Displaying RoleDto
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

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
     * <h2>Constructor for RoleDto</h2>
     * <p>
     * Constructor for RoleDto
     * </p>
     * 
     * @param role
     */
    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}