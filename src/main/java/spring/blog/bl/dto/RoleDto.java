package spring.blog.bl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Role;

/**
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
	private Long id;
	private String name;
	
	/**
	 * @param role
	 */
	public RoleDto(Role role) {
		this.id = role.getId();
		this.name = role.getName();
	}
}