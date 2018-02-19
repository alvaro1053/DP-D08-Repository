package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.User;

import services.UserService;



@Controller
@RequestMapping("/users")
public class UserController extends AbstractController {

	
	//Autowired
	@Autowired
	UserService userService;
	
	
	//Constructor
	public UserController(){
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<User> users;
		
		users = this.userService.findAll();
		
		
		result = new ModelAndView("users/list");
		result.addObject("users", users);
		return result;
	}
}
