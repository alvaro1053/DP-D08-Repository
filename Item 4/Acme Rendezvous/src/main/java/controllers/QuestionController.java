
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendeService;
import domain.Question;
import domain.Rende;

@Controller
@RequestMapping("/question")
public class QuestionController extends AbstractController {

	// Services
	@Autowired
	private RendeService	rendeService;


	// Constructors

	public QuestionController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int rendeId) {
		ModelAndView result;
		Collection<Question> res = new ArrayList<Question>();
		final String uri = "";
		Rende rende = new Rende();
		try {
			rende = this.rendeService.findOne(rendeId);
			res = rende.getQuestions();
		} catch (final Throwable oops) {
			final String message = "question.error";
			result = this.CreateListModelAndView(res, message);
		}
		result = new ModelAndView("question/list");
		result.addObject("uri", uri);
		result.addObject("questions", res);
		result.addObject("rende", rende);

		return result;

	}

	// Ancillary methods ------------------------------------------------------
	private ModelAndView CreateListModelAndView(final Collection<Question> res, final String message) {
		final ModelAndView result;
		result = new ModelAndView("question/list");
		final String uri = "";
		result.addObject("questions", res);
		result.addObject("message", message);
		result.addObject("uri", uri);
		return result;
	}
}
