package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
	@NotBlank(message = "Email field is required.")
	private String email;
	
	@NotBlank(message = "Password field is required.")
	private String password;
	
	public LoginForm(User user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
	}
}
