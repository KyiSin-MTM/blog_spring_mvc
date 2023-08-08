package spring.blog.persistence.dao.roles;

import java.util.List;

import spring.blog.persistence.entity.Role;

public interface RoleDao {

    public void saveRoleDao(Role role);

    public Role getRoleByNameDao(String roleName);

    public List<Role> getRolesDao();

    public Long dbGetRoleCount();

    public Role getRoleByIdDao(Long id);
}
