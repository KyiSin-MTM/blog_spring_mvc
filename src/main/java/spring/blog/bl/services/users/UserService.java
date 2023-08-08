package spring.blog.bl.services.users;

import java.util.List;

import javax.validation.Valid;

import spring.blog.bl.dto.UserDto;
import spring.blog.web.form.RegisterForm;

public interface UserService {

    public void saveUser(@Valid RegisterForm registerForm);

    public boolean isEqualPwdWithConfirmPwd(@Valid RegisterForm registerForm);

    public boolean doIsLoggedIn();

    public boolean checkedEmail(String email);

    public List<UserDto> getAllUsers();
}
