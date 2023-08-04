package spring.blog.common.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.entity.Role;
import spring.blog.utils.PropertyUtils;

@Component
public class DeploymentListener {
	
	@Autowired
	private RoleDao roleDao;
	
	@Value("${app.roles}")
	String appRoles;
	
	@PostConstruct
	public void dataFactory() {
		//seeding roles to database
		addRoles();
	}
	
	private void addRoles() {
		List<String> roles = PropertyUtils.parseStringListProperty(appRoles);
		for(String r:roles) {
            if(this.roleDao.dbGetRoleCount() != 2) {
                Role role = new Role();
                role.setName(r);
                roleDao.saveRoleDao(role);
            }
        }
	}
}