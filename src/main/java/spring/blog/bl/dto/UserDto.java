package spring.blog.bl.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spring.blog.persistence.entity.Role;
import spring.blog.persistence.entity.User;

/**
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class UserDto {	
	private Long id;	
	private String name;	
	private String email;	
	private List<Role> roles;	
	private String password;
	private Timestamp created_at;
	
	public UserDto() {
		super();
	}

	/**
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
