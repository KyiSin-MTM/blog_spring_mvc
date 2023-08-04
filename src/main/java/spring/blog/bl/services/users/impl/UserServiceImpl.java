package spring.blog.bl.services.users.impl;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.blog.bl.dto.CustomUserDetails;
import spring.blog.bl.services.users.UserService;
import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.Role;
import spring.blog.persistence.entity.User;
import spring.blog.web.form.LoginForm;
import spring.blog.web.form.RegisterForm;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public void saveUser(@Valid RegisterForm registerForm) {
		// TODO Auto-generated method stub
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

	@Override
	public boolean isEqualPwdWithConfirmPwd(@Valid RegisterForm registerForm) {
		// TODO Auto-generated method stub
		if(registerForm.getPassword().equals(registerForm.getConfirmPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userDao.dbFindByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid Username or Password.");
		}
		
		UserDetails userDetail = new CustomUserDetails(user.getEmail(), user.getPassword(), user.getRoles());
		return userDetail;		
	}

	@Override
	public boolean checkedLogin(@Valid LoginForm loginForm, HttpSession session) {
		// TODO Auto-generated method stub
		String email = loginForm.getEmail();
		User user = this.userDao.dbFindByEmail(email);
		
		if(user != null) {
			String dbPassword = user.getPassword();
			if(passwordEncoder.matches(loginForm.getPassword(), dbPassword)) {
				session.setAttribute("loginedUser", user);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean doIsLoggedIn() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
                && authentication.isAuthenticated();
	}

	@Override
	public boolean checkedEmail(String email) {
		// TODO Auto-generated method stub
		Long emailCount = this.userDao.dbGetCountByEmail(email);
		return emailCount > 0;
	}
}
