
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
import services.CommentService;
import services.RendeService;
import controllers.AbstractController;
import domain.Admin;
import domain.Comment;
import domain.Rende;

@Controller
@RequestMapping("/comment/admin")
public class CommentAdminController extends AbstractController {

	// Services

	@Autowired
	private CommentService	commentService;

	@Autowired
	private AdminService	adminService;

	@Autowired
	private RendeService	rendeService;


	// Constructors

	public CommentAdminController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int rendeId) {
		final ModelAndView result;
		Collection<Comment> res = new ArrayList<Comment>();
		final String uri = "/admin";
		final Admin principal = this.adminService.findByPrincipal();
		res = this.commentService.findByRendeId(rendeId);
		final Rende rende = this.rendeService.findOne(rendeId);
		final Boolean permisos = true;

		result = new ModelAndView("comment/list");
		result.addObject("principal", principal);
		result.addObject("uri", uri);
		result.addObject("comments", res);
		result.addObject("rende", rende);
		result.addObject("permisos", permisos);

		return result;

	}

	//Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int commentId) {
		ModelAndView result;
		int rendeId = 0;
		try {
			final Comment comment = this.commentService.findOne(commentId);
			rendeId = comment.getRende().getId();
			final String messageSuccessfullyDeleted = "comment.deleted";
			this.commentService.delete(comment);
			result = this.createListModelAndView(messageSuccessfullyDeleted, rendeId);
		} catch (final Throwable oops) {
			final String messageError = "comment.commit.error";
			result = this.createListModelAndView(messageError, rendeId);
		}
		return result;

	}

	protected ModelAndView createListModelAndView(final String message, final int rendeId) {
		ModelAndView result;
		final String uri = "/admin";
		Collection<Comment> comments = new ArrayList<Comment>();
		final Admin principal = this.adminService.findByPrincipal();
		final Boolean permisos = true;
		if (rendeId != 0)
			comments = this.commentService.findByRendeId(rendeId);

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("message", message);
		result.addObject("permisos", permisos);
		result.addObject("principal", principal);
		result.addObject("uri", uri);
		return result;
	}

}
