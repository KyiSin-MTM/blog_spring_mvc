package spring.blog.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@GetMapping("/home")
	public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("message", "Welcome! This Is Home Page.");
        return mv;
    }

}
