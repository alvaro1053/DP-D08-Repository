
package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendeService;
import services.UserService;
import domain.Question;
import domain.Rende;
import domain.ReplyQuestion;
import domain.User;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	//Autowired
	@Autowired
	UserService	userService;
	
	@Autowired
	RendeService	rendeService;


	//Constructor
	public UserController() {
		super();
	}

	//list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final String uri = "";
		ModelAndView result;
		Collection<User> users;

		users = this.userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("uri", uri);
		return result;
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int userId) {
		final ModelAndView result;
		User user;
		final String uri = "";

		user = this.userService.findOne(userId);

		result = new ModelAndView("user/display");
		result.addObject("user", user);
		result.addObject("uri", uri);
		result.addObject("principal", null);
		return result;

	}
	

	//Display
	@RequestMapping(value = "/displayAttendant", method = RequestMethod.GET)
	public ModelAndView displayAttendant(@RequestParam final int userId, @RequestParam final int rendeId) {
		final ModelAndView result;
		User user;
		Rende rende;
		Collection<Question> questions;
		Collection<ReplyQuestion> replyQuestions;
		Boolean viewAttendants = true;
		Map<Question, ReplyQuestion> mapQuestionsView = new HashMap<Question, ReplyQuestion>();
		final String uri = "/user";

		user = this.userService.findOne(userId);
		rende = this.rendeService.findOne(rendeId);
		
		questions = rende.getQuestions();
		replyQuestions = user.getRepliesQuestions();
		
		for(Question q : questions){
			for(ReplyQuestion rQ : replyQuestions){
				if(q.getId() == rQ.getQuestion().getId()){
					mapQuestionsView.put(q, rQ);
				}
					
			}
		}
		
		result = new ModelAndView("user/display");
		result.addObject("user", user);
		result.addObject("principal", null);
		result.addObject("viewAttendants", viewAttendants);
		result.addObject("mapQuestionsView", mapQuestionsView);
		result.addObject("rende", rende);
		result.addObject("uri", uri);
		return result;

	}
}
