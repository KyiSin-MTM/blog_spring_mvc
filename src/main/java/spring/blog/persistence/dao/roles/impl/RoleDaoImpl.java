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

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {
	
	private static final String TABLE_NAME = "Role";
	
	private static final String SELECT_STMT = "FROM " + TABLE_NAME;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveRoleDao(Role role) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(role);
	}

	@Override
	public Role getRoleByNameDao(String roleName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		String stmt = "SELECT r FROM Role r WHERE r.name = :roleName";
		return currentSession.createQuery(stmt, Role.class).setParameter("roleName", roleName).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRolesDao() {
		// TODO Auto-generated method stub
		StringBuilder stmt = new StringBuilder(SELECT_STMT);
		return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
	}

	@Override
	public Long dbGetRoleCount() {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(r) FROM Role r");
		return (Long)query.getSingleResult();
	}

	@Override
	public Role getRoleByIdDao(Long id) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().get(Role.class, id);
	}
}