/*
 * package spring.blog.bl.services.auth;
 * 
 * import javax.transaction.Transactional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import spring.blog.bl.dto.UserDto; import
 * spring.blog.persistence.dao.users.UserDao; import
 * spring.blog.persistence.entity.User;
 * 
 * @Service
 * 
 * @Transactional public class MyUserDetailsService implements
 * UserDetailsService{
 * 
 * @Autowired private UserDao userDao;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { // TODO Auto-generated method stub User user =
 * this.userDao.dbFindByEmail(username); if( user == null ) { throw new
 * UsernameNotFoundException("Invalid email or password."); };
 * 
 * UserDto userDto = new UserDto(user); return userDto; }
 * 
 * }
 */