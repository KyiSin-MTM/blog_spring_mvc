package spring.blog.persistence.dao.userProfile.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.userProfile.UserProfileDao;
import spring.blog.persistence.entity.UserProfile;

@Repository
@Transactional
public class UserProfileDaoImpl implements UserProfileDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void dbSave(UserProfile userProfile) {
        this.sessionFactory.getCurrentSession().save(userProfile);
    }

    @Override
    public UserProfile dbFindById(Long id) {
        return this.sessionFactory.getCurrentSession().get(UserProfile.class, id);
    }

    @Override
    public void dbUpdate(UserProfile userProfile) {
        this.sessionFactory.getCurrentSession().merge(userProfile);        
    }
}
