
package controllers.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import services.RendeService;
import services.ReplyQuestionService;
import services.UserService;
import controllers.AbstractController;
import domain.Question;
import domain.Rende;
import domain.ReplyQuestion;
import domain.User;
import forms.ReplyQuestionForm;

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

	@Autowired
	private RendeService			rendeService;


	// Constructors

	public ReplyQuestionUserController() {
		super();
	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendeId) {
		ModelAndView result;
		User principal = new User();
		final ReplyQuestionForm replyQuestionForm = new ReplyQuestionForm();
		final List<ReplyQuestion> replies = new ArrayList<ReplyQuestion>();
		List<Question> questions = new ArrayList<Question>();
		//Map<Question,ReplyQuestion> res = new HashMap<Question,ReplyQuestion>()
		Rende rende = new Rende();
		Boolean permisos = true;
		try {
			principal = this.userService.findByPrincipal();
			rende = this.rendeService.findOne(rendeId);
			questions = rende.getQuestions();
			replyQuestionForm.setQuestions(questions);
			replyQuestionForm.setReplies(replies);
		} catch (final Throwable oops) {
			permisos = false;
			result = this.createEditModelAndView(replyQuestionForm, permisos, "replyQuestion.commit.error");
		}
		if (principal.getrSVPS().contains(rende))
			permisos = false;

		if (rende.getQuestions().size() != 0)
			result = this.createEditModelAndView(replyQuestionForm, permisos);
		else
			result = new ModelAndView("redirect:../../rende/user/rsvp.do?rendeId=" + rendeId);

		return result;
	}

	//Save

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ReplyQuestionForm replyQuestionForm, final BindingResult binding) {
		ModelAndView result = new ModelAndView();
		final List<ReplyQuestion> replies = this.questionService.reconstruct(replyQuestionForm, binding);
		final Rende rende = replies.get(0).getQuestion().getRende();
		if (binding.hasErrors())
			result = this.createEditModelAndView(replyQuestionForm, true);
		else
			try {
				for (final ReplyQuestion rQ : replies)
					this.replyQuestionService.save(rQ);
				result = new ModelAndView("redirect:../../rende/user/rsvp.do?rendeId=" + rende.getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(replyQuestionForm, true, "question.commit.error");
			}
		return result;

	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ReplyQuestionForm replyQuestionForm, final Boolean permisos) {
		ModelAndView result;

		result = this.createEditModelAndView(replyQuestionForm, permisos, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ReplyQuestionForm replyQuestionForm, final Boolean permisos, final String message) {
		final ModelAndView result;

		final User principal = this.userService.findByPrincipal();
		result = new ModelAndView("replyQuestion/edit");
		result.addObject("replyQuestionForm", replyQuestionForm);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("message", message);

		return result;
	}
}
