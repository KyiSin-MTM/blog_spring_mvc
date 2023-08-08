package spring.blog.bl.services.users.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spring.blog.bl.dto.CustomUserDetails;
import spring.blog.bl.dto.UserDto;
import spring.blog.bl.services.users.UserService;
import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.Role;
import spring.blog.persistence.entity.User;
import spring.blog.web.form.RegisterForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>roleDao</h2>
     * <p>
     * roleDao
     * </p>
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * <h2>saveUser</h2>
     * <p>
     * save user
     * </p>
     * 
     * @param registerForm
     */
    @Override
    public void saveUser(@Valid RegisterForm registerForm) {
        User user = new User();
        user.setName(registerForm.getName());
        user.setEmail(registerForm.getEmail());
        List<Role> roles = new ArrayList<Role>();
        Long id = registerForm.getRole().getId();
        Role selectedRole = this.roleDao.getRoleByIdDao(id);
        roles.add(selectedRole);
        user.setRoles(roles);
        user.setPassword(registerForm.getPassword());
        this.userDao.saveUserDao(user);
    }

    /**
     * <h2>isEqualPwdWithConfirmPwd</h2>
     * <p>
     * check two passwords
     * </p>
     * 
     * @param registerForm
     * @return
     */
    @Override
    public boolean isEqualPwdWithConfirmPwd(@Valid RegisterForm registerForm) {
        if (registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
            return true;
        }
        return false;
    }

    /**
     * <h2>loadUserByUsername</h2>
     * <p>
     * check authentication
     * </p>
     * 
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userDao.dbFindByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password.");
        }
        UserDetails userDetail = new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRoles());
        return userDetail;
    }

    /**
     * <h2>doIsLoggedIn</h2>
     * <p>
     * check logged in or not
     * </p>
     * 
     * @return
     */
    @Override
    public boolean doIsLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
    }

    /**
     * <h2>checkedEmail</h2>
     * <p>
     * check email exists or not
     * </p>
     * 
     * @param email
     * @return
     */
    @Override
    public boolean checkedEmail(String email) {
        Long emailCount = this.userDao.dbGetCountByEmail(email);
        return emailCount > 0;
    }

    /**
     * <h2>getAllUsers</h2>
     * <p>
     * users list
     * </p>
     * 
     * @return
     */
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userDao.dbGetAllUsers();
        List<UserDto> userDtoList = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
        return userDtoList;
    }
}
