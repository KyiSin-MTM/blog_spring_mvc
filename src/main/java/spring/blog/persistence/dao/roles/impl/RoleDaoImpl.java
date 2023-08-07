package spring.blog.persistence.dao.roles.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.entity.Role;

/**
 * <h2>RoleDaoImpl Class</h2>
 * <p>
 * Process for Displaying RoleDaoImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    /**
     * <h2>TABLE_NAME</h2>
     * <p>
     * TABLE_NAME
     * </p>
     */
    private static final String TABLE_NAME = "Role";

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
    private SessionFactory sessionFactory;

    /**
     * <h2>saveRoleDao</h2>
     * <p>
     * 
     * </p>
     * 
     * @param role
     */
    @Override
    public void saveRoleDao(Role role) {
        this.sessionFactory.getCurrentSession().save(role);
    }

    /**
     * <h2>getRoleByNameDao</h2>
     * <p>
     * 
     * </p>
     * 
     * @param roleName
     * @return
     */
    @Override
    public Role getRoleByNameDao(String roleName) {
        Session currentSession = sessionFactory.getCurrentSession();
        String stmt = "SELECT r FROM Role r WHERE r.name = :roleName";
        return currentSession.createQuery(stmt, Role.class).setParameter("roleName", roleName).getSingleResult();
    }

    /**
     * <h2>getRolesDao</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRolesDao() {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
    }

    /**
     * <h2>dbGetRoleCount</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @Override
    public Long dbGetRoleCount() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(r) FROM Role r");
        return (Long) query.getSingleResult();
    }

    /**
     * <h2>getRoleByIdDao</h2>
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    @Override
    public Role getRoleByIdDao(Long id) {
        return this.sessionFactory.getCurrentSession().get(Role.class, id);
    }
}