
package controllers.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.ReplyCommentService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.ReplyComment;
import domain.User;

@Controller
@RequestMapping("/replyComment/user")
public class ReplyCommentUserController extends AbstractController {

	// Services

	@Autowired
	private ReplyCommentService	replyCommentService;

	@Autowired
	private UserService			userService;

	@Autowired
	private CommentService		commentService;

	@Autowired
	private Validator		validator;

	// Constructors

	public ReplyCommentUserController() {
		super();
	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int commentId) {
		final ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		final ReplyComment replyComment = this.replyCommentService.create();
		Boolean permisos = true;
		final Comment comment = this.commentService.findOne(commentId);
		if (comment == null)
			permisos = false;
		else if (!(principal.getrSVPS().contains(comment.getRende())))
			permisos = false;
		else
			replyComment.setComment(comment);
		result = this.createEditModelAndView(replyComment, permisos);

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final ReplyComment replyComment, final BindingResult binding) {
		ModelAndView result;
		validator.validate(replyComment,binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(replyComment, true);
		else
			try {
				this.replyCommentService.save(replyComment);
				result = new ModelAndView("redirect:../../comment/user/list.do?rendeId=" + replyComment.getComment().getRende().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(replyComment, true, "comment.commit.error");
			}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ReplyComment comment, final Boolean permisos) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, permisos, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ReplyComment comment, final Boolean permisos, final String message) {
		final ModelAndView result;

		final User principal = this.userService.findByPrincipal();
		result = new ModelAndView("replyComment/edit");
		result.addObject("replyComment", comment);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("message", message);

		return result;
	}
}
