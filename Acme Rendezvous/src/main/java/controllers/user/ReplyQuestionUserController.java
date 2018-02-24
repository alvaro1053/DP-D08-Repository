
package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import services.ReplyQuestionService;
import services.UserService;
import controllers.AbstractController;
import domain.Question;
import domain.ReplyQuestion;
import domain.User;

@Controller
@RequestMapping("/replyQuestion/user")
public class ReplyQuestionUserController extends AbstractController {

	// Services

	@Autowired
	private ReplyQuestionService	replyQuestionService;

	@Autowired
	private UserService				userService;

	@Autowired
	private QuestionService			questionService;


	// Constructors

	public ReplyQuestionUserController() {
		super();
	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int questionId) {
		final ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		final ReplyQuestion replyQuestion = this.replyQuestionService.create();
		Boolean permisos = true;
		final Question question = this.questionService.findOne(questionId);
		final Collection<Question> repliedByUser = this.questionService.SelectRepliedByUserId(principal.getId());
		if (question == null)
			permisos = false;
		else if (!(principal.getrSVPS().contains(question.getRende())) || repliedByUser.contains(question) || principal.getRendes().contains(question.getRende()))
			permisos = false;
		else
			replyQuestion.setQuestion(question);
		result = this.createEditModelAndView(replyQuestion, permisos);

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ReplyQuestion replyQuestion, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(replyQuestion, true);
		else
			try {
				this.replyQuestionService.save(replyQuestion);
				result = new ModelAndView("redirect:../../question/user/list.do?rendeId=" + replyQuestion.getQuestion().getRende().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(replyQuestion, true, "question.commit.error");
			}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ReplyQuestion question, final Boolean permisos) {
		ModelAndView result;

		result = this.createEditModelAndView(question, permisos, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ReplyQuestion question, final Boolean permisos, final String message) {
		final ModelAndView result;

		final User principal = this.userService.findByPrincipal();
		result = new ModelAndView("replyQuestion/edit");
		result.addObject("replyQuestion", question);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("message", message);

		return result;
	}
}
