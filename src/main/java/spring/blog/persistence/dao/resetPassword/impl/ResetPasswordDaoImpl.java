package spring.blog.persistence.dao.resetPassword.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.resetPassword.ResetPasswordDao;
import spring.blog.persistence.entity.ResetPassword;

/**
 * <h2>ResetPasswordDaoImpl Class</h2>
 * <p>
 * Process for Displaying ResetPasswordDaoImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Repository
@Transactional
public class ResetPasswordDaoImpl implements ResetPasswordDao {

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>save</h2>
     * <p>
     * reset password save
     * </p>
     * 
     * @param resetPassword
     */
    @Override
    public void save(ResetPassword resetPassword) {
        this.sessionFactory.getCurrentSession().save(resetPassword);
    }

    /**
     * <h2>update</h2>
     * <p>
     * reset password update
     * </p>
     * 
     * @param resetPassword
     */
    @Override
    public void update(ResetPassword resetPassword) {
        this.sessionFactory.getCurrentSession().merge(resetPassword);
    }

    /**
     * <h2>findByTokenDao</h2>
     * <p>
     * get reset password by token
     * </p>
     * 
     * @param token
     * @return
     */
    @Override
    public ResetPassword findByTokenDao(String token) {
        ResetPassword resetPassword = (ResetPassword) this.sessionFactory.getCurrentSession()
                .createQuery("SELECT r FROM ResetPassword r where r.token = :token").setParameter("token", token)
                .uniqueResult();
        return resetPassword;
    }
}
