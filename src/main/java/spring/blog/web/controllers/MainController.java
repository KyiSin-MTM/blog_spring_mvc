package spring.blog.web.controllers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <h2>MainController Class</h2>
 * <p>
 * Process for Displaying MainController
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Controller
public class MainController {

    /**
     * <h2>home</h2>
     * <p>
     * home page
     * </p>
     *
     * @param session
     * @param authentication
     * @return
     * @throws IOException
     * @return ModelAndView
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Authentication authentication) throws IOException {
        ModelAndView mv = new ModelAndView();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            mv.setViewName("redirect:/users");
            return mv;
        } else {
            mv.setViewName("redirect:/posts");
            return mv;
        }
    }
}
