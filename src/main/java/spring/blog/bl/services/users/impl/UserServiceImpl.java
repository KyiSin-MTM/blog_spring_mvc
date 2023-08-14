package spring.blog.bl.services.users.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
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
import spring.blog.persistence.dao.userProfile.UserProfileDao;
import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.Role;
import spring.blog.persistence.entity.User;
import spring.blog.persistence.entity.UserProfile;
import spring.blog.web.form.UserEditForm;
import spring.blog.web.form.UserForm;

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
     * <h2>userProfileDao</h2>
     * <p>
     * userProfileDao
     * </p>
     */
    @Autowired
    private UserProfileDao userProfileDao;

    /**
     * <h2>session</h2>
     * <p>
     * session
     * </p>
     */
    @Autowired
    HttpSession session;

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
    public void saveUser(@Valid UserForm registerForm) {
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
    public boolean isEqualPwdWithConfirmPwd(@Valid UserForm registerForm) {
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
     * <h2>doGetLoginInfo</h2>
     * <p>
     * get logged in info
     * </p>
     * 
     * @return
     */
    @Override
    public User doGetLoginInfo() {
        String user_email = null;
        // TODO Auto-generated method stub
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            user_email = ((UserDetails) principal).getUsername();
        } else {
            user_email = principal.toString();
        }
        User user = userDao.dbFindByEmail(user_email);
        return user;
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

    /**
     * <h2>updateUser</h2>
     * <p>
     * update user
     * </p>
     * 
     * @param userEditForm
     */
    @Override
    public void updateUser(@Valid UserEditForm userEditForm) {
        User user = this.userDao.dbFindUserById(userEditForm.getId());
        user.setName(userEditForm.getName());
        user.setEmail(userEditForm.getEmail());
        this.userDao.dbUpdate(user);
        if (userEditForm.getPhoto() != null) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formattedDateTime = now.format(formatter);
            String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources"
                    + File.separator + "images" + File.separator + formattedDateTime + "_" + userEditForm.getPhoto().getOriginalFilename();
            File directory = new File(path).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try {
                FileOutputStream fileout = new FileOutputStream(path);
                fileout.write(userEditForm.getPhoto().getBytes());
                fileout.close();
                if(user.getUserProfile().getPhotoPath() != null) {
                    String oldImagePath = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + user.getUserProfile().getPhotoPath();
                    File oldImage = new File(oldImagePath);
                    if(oldImage.exists()) {
                        oldImage.delete();
                    }
                }               
            } catch (Exception e) {
                e.printStackTrace();
            }
            String imagePath = "resources/images/" + formattedDateTime + "_" + userEditForm.getPhoto().getOriginalFilename();
            if (user.getUserProfile() == null) {
                UserProfile userProfile = new UserProfile();
                userProfile.setPhotoPath(imagePath);
                userProfile.setUser(user);
                this.userProfileDao.dbSave(userProfile);
            } else {
                UserProfile userProfile = this.userProfileDao.dbFindById(user.getUserProfile().getId());
                userProfile.setPhotoPath(imagePath);
                userProfile.setUser(user);
                this.userProfileDao.dbUpdate(userProfile);
            }
        }
    }

    /**
     * <h2>getSearchUsers</h2>
     * <p>
     * searched user list
     * </p>
     * 
     * @param searchKey
     * @return
     */
    @Override
    public List<UserDto> getSearchUsers(String searchKey) {
        if (searchKey != "") {
            List<User> users = this.userDao.getSearchUsersDao(searchKey);
            List<UserDto> userDtoList = users.stream().map(user -> new UserDto(user)).collect(Collectors.toList());
            return userDtoList;
        } else {
            return this.getAllUsers();
        }
    }

    /**
     * <h2> deleteUserById </h2>
     * <p>
     * delete user by id
     * </p>
     * 
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        User user = this.userDao.dbFindUserById(id);
        if(user.getUserProfile().getPhotoPath() != null) {
            String oldImagePath = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + user.getUserProfile().getPhotoPath();
            File oldImage = new File(oldImagePath);
            if(oldImage.exists()) {
                oldImage.delete();
            }
        }    
        this.userDao.deleteUserByIdDao(user);        
    }
}
