package spring.blog.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import spring.blog.persistence.dao.users.UserDao;
import spring.blog.persistence.entity.User;

/**
 * <h2>UserProfileController Class</h2>
 * <p>
 * Process for Displaying UserProfileController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class UserProfileController {

    /**
     * <h2>userDao</h2>
     * <p>
     * userDao
     * </p>
     */
    @Autowired
    private UserDao userDao;

    /**
     * <h2>profile</h2>
     * <p>
     * view profile
     * </p>
     *
     * @param authentication
     * @return
     * @return ModelAndView
     */
    @RequestMapping("/profile")
    public ModelAndView profile(Authentication authentication) {
        ModelAndView mv = new ModelAndView("profileView");
        User user = this.userDao.dbFindByEmail(authentication.getName());
        mv.addObject("authUser", user);
        return mv;
    }
}
