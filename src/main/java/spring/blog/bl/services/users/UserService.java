package spring.blog.bl.services.users;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import spring.blog.bl.dto.UserDto;
import spring.blog.web.form.LoginForm;
import spring.blog.web.form.RegisterForm;

public interface UserService {

    void saveUser(@Valid RegisterForm registerForm);

    boolean isEqualPwdWithConfirmPwd(@Valid RegisterForm registerForm);

    boolean checkedLogin(@Valid LoginForm loginForm, HttpSession session);

    boolean doIsLoggedIn();

    boolean checkedEmail(String email);

    List<UserDto> getAllUsers();
}
