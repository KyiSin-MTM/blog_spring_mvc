package spring.blog.common.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import spring.blog.persistence.dao.roles.RoleDao;
import spring.blog.persistence.entity.Role;
import spring.blog.utils.PropertyUtils;

/**
 * <h2>DeploymentListener Class</h2>
 * <p>
 * Process for Displaying DeploymentListener
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Component
public class DeploymentListener {

    /**
     * <h2>roleDao</h2>
     * <p>
     * roleDao
     * </p>
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * <h2>appRoles</h2>
     * <p>
     * appRoles
     * </p>
     */
    @Value("${app.roles}")
    String appRoles;

    /**
     * <h2>dataFactory</h2>
     * <p>
     * data factory
     * </p>
     *
     * @return void
     */
    @PostConstruct
    public void dataFactory() {
        // seeding roles to database
        addRoles();
    }

    /**
     * <h2>addRoles</h2>
     * <p>
     * add role in database
     * </p>
     *
     * @return void
     */
    private void addRoles() {
        List<String> roles = PropertyUtils.parseStringListProperty(appRoles);
        for (String r : roles) {
            if (this.roleDao.dbGetRoleCount() != 2) {
                Role role = new Role();
                role.setName(r);
                roleDao.saveRoleDao(role);
            }
        }
    }
}