
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendeService;
import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/user/user")
public class UserUserController extends AbstractController {

	//Autowired
	@Autowired
	UserService		userService;

	@Autowired
	RendeService	rendeService;


	//Constructor
	public UserUserController() {
		super();
	}

	//list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final String uri = "/user";
		ModelAndView result;
		Collection<User> users;

		final User principal = this.userService.findByPrincipal();
		users = this.userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("uri", uri);
		result.addObject("users", users);
		result.addObject("principal", principal);

		return result;
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int userId) {
		final ModelAndView result;
		User user;
		final User principal = this.userService.findByPrincipal();
		final String uri = "/user";
		final Boolean viewAttendants = false;

		user = this.userService.findOne(userId);

		result = new ModelAndView("user/display");
		result.addObject("user", user);
		result.addObject("principal", principal);
		result.addObject("viewAttendants", viewAttendants);
		result.addObject("uri", uri);
		return result;

	}

}
