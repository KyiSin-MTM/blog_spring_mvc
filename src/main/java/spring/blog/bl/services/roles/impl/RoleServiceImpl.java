package spring.blog.bl.services.roles.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.blog.bl.dto.RoleDto;
import spring.blog.bl.services.roles.RoleService;
import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.entity.Role;

/**
 * <h2>RoleServiceImpl Class</h2>
 * <p>
 * Process for Displaying RoleServiceImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * <h2>roleDao</h2>
     * <p>
     * roleDao
     * </p>
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * <h2>getRoles</h2>
     * <p>
     * role list
     * </p>
     * 
     * @return
     */
    @Override
    public List<RoleDto> getRoles() {
        List<Role> roles = this.roleDao.getRolesDao();
        List<RoleDto> roleDtoList = roles.stream().map(role -> new RoleDto(role)).collect(Collectors.toList());
        return roleDtoList;
    }
}
