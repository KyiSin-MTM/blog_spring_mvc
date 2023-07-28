package spring.blog.persistence.dao.roles.impl;

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
}