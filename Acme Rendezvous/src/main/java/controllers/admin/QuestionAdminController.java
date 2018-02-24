
package controllers.admin;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.QuestionService;
import services.RendeService;
import controllers.AbstractController;
import domain.Admin;
import domain.Question;
import domain.Rende;

@Controller
@RequestMapping("/question/admin")
public class QuestionAdminController extends AbstractController {

	// Services

	@Autowired
	private QuestionService	questionService;

	@Autowired
	private AdminService	adminService;

	@Autowired
	private RendeService	rendeService;


	// Constructors

	public QuestionAdminController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int rendeId) {
		ModelAndView result;
		Collection<Question> res = new ArrayList<Question>();
		final String uri = "/admin";
		final Admin principal = this.adminService.findByPrincipal();
		Boolean permisos = true;
		Rende rende = new Rende();
		final Collection<Question> repliedByPrincipal = new ArrayList<Question>();
		try {
			rende = this.rendeService.findOne(rendeId);
			res = rende.getQuestions();
		} catch (final Throwable oops) {
			final String message = "question.error";
			permisos = false;
			result = this.CreateListModelAndView(res, message, permisos);
		}
		result = new ModelAndView("question/list");
		result.addObject("permisos", permisos);
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
		Boolean permisos = false;
		try {
			final Question question = this.questionService.findOne(questionId);
			r = question.getRende();
			res = r.getQuestions();
			res.remove(question);
			this.questionService.delete(question);
			permisos = true;
			final String message = "question.deleted";
			result = this.CreateListModelAndView(res, message, permisos);
		} catch (final Throwable oops) {
			result = this.CreateListModelAndView(res, "question.commit.error", false);
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------
	private ModelAndView CreateListModelAndView(final Collection<Question> res, final String message, final Boolean permisos) {
		final ModelAndView result;
		final Admin principal = this.adminService.findByPrincipal();
		result = new ModelAndView("question/list");
		final String uri = "/admin";
		result.addObject("principal", principal);
		result.addObject("questions", res);
		result.addObject("message", message);
		result.addObject("permisos", permisos);
		result.addObject("uri", uri);
		return result;
	}
}
