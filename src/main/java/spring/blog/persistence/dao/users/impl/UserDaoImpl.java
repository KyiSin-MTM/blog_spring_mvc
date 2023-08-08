package spring.blog.persistence.dao.users.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.User;

/**
 * <h2>UserDaoImpl Class</h2>
 * <p>
 * Process for Displaying UserDaoImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    /**
     * <h2>TABLE_NAME</h2>
     * <p>
     * TABLE_NAME
     * </p>
     */
    private static final String TABLE_NAME = "User";

    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * SELECT_STMT
     * </p>
     */
    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    SessionFactory sessionFactory;

    /**
     * <h2>saveUserDao</h2>
     * <p>
     * save user
     * </p>
     * 
     * @param user
     */
    @Override
    public void saveUserDao(User user) {
        this.sessionFactory.getCurrentSession().save(user);
    }

    /**
     * <h2>dbGetCountByEmail</h2>
     * <p>
     * Get count by email
     * </p>
     * 
     * @param email
     * @return
     */
    @Override
    public Long dbGetCountByEmail(String email) {
        return (Long) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email").setParameter("email", email)
                .uniqueResult();
    }

    /**
     * <h2>dbGetPasswordByEmail</h2>
     * <p>
     * Get password by email
     * </p>
     * 
     * @param email
     * @return
     */
    @Override
    public String dbGetPasswordByEmail(String email) {
        String password = (String) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT u.password FROM User u WHERE u.email = :email").setParameter("email", email)
                .uniqueResult();
        return password;
    }

    /**
     * <h2>dbFindByEmail</h2>
     * <p>
     * find user by email
     * </p>
     * 
     * @param email
     * @return
     */
    @Override
    public User dbFindByEmail(String email) {
        User user = (User) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).uniqueResult();
        return user;
    }

    /**
     * <h2>dbGetAllUsers</h2>
     * <p>
     * get user list
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> dbGetAllUsers() {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
    }

    /**
     * <h2>dbUpdate</h2>
     * <p>
     * user update
     * </p>
     * 
     * @param user
     */
    @Override
    public void dbUpdate(User user) {
        this.sessionFactory.getCurrentSession().merge(user);
    }
}
