
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RendeService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rende;
import domain.User;
import forms.CommentForm;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services

	@Autowired
	private CommentService	commentService;

	@Autowired
	private UserService		userService;

	@Autowired
	private RendeService	rendeService;


	// Constructors

	public CommentUserController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int rendeId) {
		final ModelAndView result;
		Collection<Comment> res = new ArrayList<Comment>();
		final String uri = "/user";
		final User principal = this.userService.findByPrincipal();
		res = this.commentService.findByRendeId(rendeId);
		final Rende rende = this.rendeService.findOne(rendeId);
		Boolean permisos = false;
		if (principal.getrSVPS().contains(rende))
			permisos = true;

		result = new ModelAndView("comment/list");
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("uri", uri);
		result.addObject("comments", res);
		result.addObject("rende", rende);

		return result;

	}

	//Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendeId) {
		ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		final CommentForm comment = new CommentForm();
		final Rende rende = this.rendeService.findOne(rendeId);
		comment.setRende(rendeId);
		Boolean permisos = false;
		if (principal.getrSVPS().contains(rende))
			permisos = true;

		result = this.createEditModelAndView(comment, permisos);

		return result;
	}
	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		final User principal = this.userService.findByPrincipal();
		Boolean permisos = false;
		final CommentForm commentForm = new CommentForm();
		final Comment comment = this.commentService.findOne(commentId);
		if (comment != null) {
			commentForm.setId(comment.getId());
			commentForm.setText(comment.getText());
			commentForm.setPicture(comment.getPicture());
			commentForm.setRende(comment.getRende().getId());
			if (principal.getrSVPS().contains(comment.getRende()) && principal.getComments().contains(comment))
				permisos = true;
		}
		result = this.createEditModelAndView(commentForm, permisos);
		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final CommentForm commentForm, final BindingResult binding) {
		ModelAndView result;
		final Comment comment = this.commentService.reconstruct(commentForm, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(commentForm, true);
		else
			try {
				this.commentService.save(comment);
				result = new ModelAndView("redirect:../user/list.do?rendeId=" + comment.getRende().getId());
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(commentForm, true, "comment.commit.error");
			}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final CommentForm comment, final Boolean permisos) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, permisos, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final CommentForm comment, final Boolean permisos, final String message) {
		final ModelAndView result;

		final User principal = this.userService.findByPrincipal();
		result = new ModelAndView("comment/edit");
		result.addObject("commentForm", comment);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		result.addObject("message", message);

		return result;
	}
}
