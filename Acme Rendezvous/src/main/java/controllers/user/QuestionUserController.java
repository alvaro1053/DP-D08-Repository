
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import services.RendeService;
import services.UserService;
import controllers.AbstractController;
import domain.Question;
import domain.Rende;
import domain.User;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	// Services

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private UserService		userService;

	@Autowired
	private RendeService	rendeService;


	// Constructors

	public QuestionUserController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int rendeId) {
		ModelAndView result;
		Collection<Question> res = new ArrayList<Question>();
		final String uri = "/user";
		final User principal = this.userService.findByPrincipal();
		Rende rende = new Rende();
		Collection<Question> repliedByPrincipal = new ArrayList<Question>();
		try {
			repliedByPrincipal = this.questionService.SelectRepliedByUserId(principal.getId());
			rende = this.rendeService.findOne(rendeId);
			res = rende.getQuestions();
		} catch (final Throwable oops) {
			final String message = "question.error";
			result = this.CreateListModelAndView(res, message);
		}
		result = new ModelAndView("question/list");
		result.addObject("principal", principal);
		result.addObject("uri", uri);
		result.addObject("questions", res);
		result.addObject("replied", repliedByPrincipal);
		result.addObject("rende", rende);

		return result;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int questionId) {
		ModelAndView result;
		Rende r = new Rende();
		Collection<Question> res = new ArrayList<Question>();
		try {
			final Question question = this.questionService.findOne(questionId);
			r = question.getRende();
			res = r.getQuestions();
			res.remove(question);
			this.questionService.delete(question);
			final String message = "question.deleted";
			result = this.CreateListModelAndView(res, message);
		} catch (final Throwable oops) {
			result = this.CreateListModelAndView(res, "question.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	private ModelAndView CreateListModelAndView(final Collection<Question> res, final String message) {
		final ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		result = new ModelAndView("question/list");
		final String uri = "/user";
		result.addObject("principal", principal);
		result.addObject("questions", res);
		result.addObject("message", message);
		result.addObject("uri", uri);
		return result;
	}
}
