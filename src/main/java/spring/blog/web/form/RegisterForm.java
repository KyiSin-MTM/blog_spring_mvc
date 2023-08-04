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

/**
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {

	private Long id;

	@NotBlank(message = "Name field is required")
	private String name;

	@NotBlank(message = "Email field is required")
	@Email(message = "Email format is invalid")
	private String email;

//	@Min(value = 1, message = "Role field is required")
//	private Long roleId;

	private List<Role> roles;

	private Role role;

	@NotBlank(message = "Password field is required")
	private String password;

	@NotBlank(message = "Confirm password field is required")
	private String confirmPassword;

	/*
	 * public RegisterForm(UserDto userDTO) { this.id = userDTO.getId(); this.name =
	 * userDTO.getName(); this.email = userDTO.getEmail(); this.password =
	 * userDTO.getPassword(); this.roles = userDTO.getRoles().stream().map(role ->
	 * role.getAuthority()).collect(Collectors.toList()); }
	 */

	public RegisterForm(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getName();
		this.roles = user.getRoles();
		this.password = user.getPassword();
	}

}