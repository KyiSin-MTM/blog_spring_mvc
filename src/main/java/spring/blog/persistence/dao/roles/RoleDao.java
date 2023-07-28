package spring.blog.persistence.dao.roles;

import spring.blog.persistence.entity.Role;

public interface RoleDao {
	
	void saveRoleDao(Role role);

	Role getRoleByNameDao(String roleName);
}
