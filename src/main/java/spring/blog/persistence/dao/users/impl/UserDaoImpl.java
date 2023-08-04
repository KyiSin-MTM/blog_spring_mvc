package spring.blog.persistence.dao.users.impl;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void saveUserDao(User user) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public Long dbGetCountByEmail(String email) {
		// TODO Auto-generated method stub
		return (Long)this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email").setParameter("email", email).uniqueResult();
	}

	@Override
	public String dbGetPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		String password = (String)this.sessionFactory.getCurrentSession().createQuery("SELECT u.password FROM User u WHERE u.email = :email").setParameter("email", email).uniqueResult();
		System.out.println(password);
		return password;
	}

	@Override
	public User dbFindByEmail(String email) {
		// TODO Auto-generated method stub
		User user = (User) this.sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.email = :email").setParameter("email", email).uniqueResult();
		return user;
	}
}
