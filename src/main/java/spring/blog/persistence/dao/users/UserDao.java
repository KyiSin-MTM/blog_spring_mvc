package spring.blog.persistence.dao.users;

import java.util.List;

import spring.blog.persistence.entity.User;

public interface UserDao {

    public void saveUserDao(User user);

    public Long dbGetCountByEmail(String email);

    public String dbGetPasswordByEmail(String email);

    public User dbFindByEmail(String username);

    public List<User> dbGetAllUsers();

    public void dbUpdate(User user);

    public User dbFindUserById(Long id);

    public List<User> getSearchUsersDao(String searchKey);

    public void deleteUserByIdDao(User user);
}
