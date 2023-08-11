package spring.blog.bl.services.users;

import java.util.List;

import javax.validation.Valid;

import spring.blog.bl.dto.UserDto;
import spring.blog.persistence.entity.User;
import spring.blog.web.form.UserEditForm;
import spring.blog.web.form.UserForm;

public interface UserService {

    public void saveUser(@Valid UserForm registerForm);

    public boolean isEqualPwdWithConfirmPwd(@Valid UserForm registerForm);

    public boolean doIsLoggedIn();

    public boolean checkedEmail(String email);

    public List<UserDto> getAllUsers();

    public void updateUser(@Valid UserEditForm userEditForm);

    public User doGetLoginInfo();

    public List<UserDto> getSearchUsers(String searchKey);

    public void deleteUserById(Long id);
}
