package spring.blog.bl.services.roles.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.blog.bl.dto.RoleDto;
import spring.blog.bl.services.roles.RoleService;
import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<RoleDto> getRoles() {
		// TODO Auto-generated method stub
		List<Role> roles = this.roleDao.getRolesDao();
		for (Role role : roles) {
		    System.out.println(role);
		}
		List<RoleDto> roleDtoList = roles.stream().map(role -> {
            RoleDto roleDto = new RoleDto(role);
            return roleDto;
        }).collect(Collectors.toList());

		return roleDtoList;
	}	
}
