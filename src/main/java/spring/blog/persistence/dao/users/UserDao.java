package spring.blog.persistence.dao.users;

import spring.blog.persistence.entity.User;

public interface UserDao {

	void saveUserDao(User user);

	Long dbGetCountByEmail(String email);

	String dbGetPasswordByEmail(String email);

	User dbFindByEmail(String username);

}
