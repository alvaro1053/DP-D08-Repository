
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
import services.ReplyCommentService;
import domain.Admin;
import domain.Comment;
import domain.Rende;
import domain.ReplyComment;

@Controller
@RequestMapping("/replyComment/admin")
public class ReplyCommentAdminController {

	// Services

	@Autowired
	private ReplyCommentService	replyCommentService;

	@Autowired
	private AdminService		adminService;


	//Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int replyCommentId) {
		ModelAndView result;
		Rende rende = null;
		try {
			final ReplyComment replyCmment = this.replyCommentService.findOne(replyCommentId);
			rende = replyCmment.getComment().getRende();
			final String messageSuccessfullyDeleted = "replyComment.deleted";
			this.replyCommentService.delete(replyCmment);
			result = this.createListModelAndView(messageSuccessfullyDeleted, rende);
		} catch (final Throwable oops) {
			final String messageError = "replycomment.commit.error";
			result = this.createListModelAndView(messageError, rende);
		}
		return result;

	}

	private ModelAndView createListModelAndView(final String message, final Rende rende) {

		ModelAndView result;
		final String uri = "/admin";
		Collection<Comment> comments = new ArrayList<Comment>();
		final Admin principal = this.adminService.findByPrincipal();
		final Boolean permisos = true;
		if (rende != null)
			comments = rende.getComments();

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("message", message);
		result.addObject("permisos", permisos);
		result.addObject("principal", principal);
		result.addObject("uri", uri);
		return result;
	}
}
