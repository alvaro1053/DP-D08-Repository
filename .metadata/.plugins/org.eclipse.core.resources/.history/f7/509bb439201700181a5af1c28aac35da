
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.UserService;
import controllers.AbstractController;
import domain.Comment;
import domain.User;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	// Services

	@Autowired
	private CommentService	commentService;

	@Autowired
	private UserService		userService;


	// Constructors

	public CommentUserController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView filter(@RequestParam final int rendeId) {
		final ModelAndView result;
		Collection<Comment> res = new ArrayList<Comment>();
		final User principal = this.userService.findByPrincipal();
		final String uri = "/user";
		res = this.commentService.findByRendeId(rendeId);

		result = new ModelAndView("comment/list");
		result.addObject("principal", principal);
		result.addObject("uri", uri);
		result.addObject("comments", res);

		return result;

	}

}
