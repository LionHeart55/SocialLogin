package zone.cognitive.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import zone.cognitive.dto.AppUser;
import zone.cognitive.dto.LocalUser;
import zone.cognitive.dto.SocialUser;
import zone.cognitive.dto.UserRegistrationForm;
import zone.cognitive.security.service.UserAlreadyExistAuthenticationException;
import zone.cognitive.service.UserService;

/**
 * @author <a href="mailto:cognitive.zone@gmail.com">Shahar Shocron</a>
 * @since 07/04/17
 */
@RestController
@SessionAttributes("loggedUser")
public class PagesController {

	final private static Logger LOGGER = LoggerFactory.getLogger(PagesController.class);

	@Autowired
    private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Login Page");
        model.setViewName("login");
        return model;
    }

	@RequestMapping(value = {"/","/userpage"}, method = RequestMethod.GET)
    public ModelAndView userPage(HttpSession session) {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Welcome");
        AppUser appUser = setUser();
        if(appUser != null) {
        	model.addObject("loggedUser", appUser);
        } else {
        	model.addObject("message", "AppUser not created.");
        }
        model.setViewName("main");
        return model;
    }
    
    @RequestMapping(value = {"/accessdenied"}, method = RequestMethod.GET)
    public ModelAndView accessDeniedPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Either username or password is incorrect.");
        model.setViewName("accessdenied");
        return model;
    }

    private AppUser setUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOGGER.debug("Loading user from UserDetails: {}", principal);
        
        AppUser appUser = null;
        if (principal instanceof LocalUser) {
	        	//LocalUser localUser = ((LocalUser) principal);
	        	appUser = AppUser.buildAppUser( ((LocalUser) principal) );
	        	//LOGGER.debug("Loading user from LocalUser: {}", logge	appUser.getUsername());
        } else if (principal instanceof SocialUser) {
	        	//SocialUser socialUser = ((SocialUser) principal);
	        	appUser = AppUser.buildAppUser( ((SocialUser) principal) );
            //LOGGER.debug("Loading user from LocalUser: {}", appUser.getUsername());
        } 
        
        return appUser;
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "User Registration Form");
        model.setViewName("registration");
        return model;
    }

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody String registerUser(@RequestBody UserRegistrationForm registrationForm) throws UserAlreadyExistAuthenticationException {

        if (registrationForm.getUserId() == null) {
            registrationForm.setUserId(registrationForm.getUserId());
        }

        LocalUser localUser = (LocalUser) userService.registerNewUser(registrationForm);

       return "success";

    }
}
