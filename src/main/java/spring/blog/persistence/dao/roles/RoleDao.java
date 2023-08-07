package spring.blog.persistence.dao.roles;

import java.util.List;

import spring.blog.persistence.entity.Role;

public interface RoleDao {

    void saveRoleDao(Role role);

    Role getRoleByNameDao(String roleName);

    List<Role> getRolesDao();

    Long dbGetRoleCount();

    Role getRoleByIdDao(Long id);
}
