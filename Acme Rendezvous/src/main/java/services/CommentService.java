
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import domain.Admin;
import domain.Comment;
import domain.Rende;
import domain.ReplyComment;
import domain.User;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	// Managed Repository
	@Autowired
	private CommentRepository	commentRepository;

	// Managed services
	@Autowired
	private UserService			userService;

	@Autowired
	private RendeService		rendeService;

	@Autowired
	private AdminService		adminService;

	@Autowired
	private ReplyCommentService	replyCommentService;

	@Autowired
	private Validator			validator;


	//Constructor
	public CommentService() {
		super();
	}

	public Comment create() {
		User principal;
		Comment comment;
		Date moment;
		final Collection<ReplyComment> repliesComments = new ArrayList<ReplyComment>();

		moment = new Date(System.currentTimeMillis() - 1);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		comment = new Comment();

		comment.setMoment(moment);
		comment.setRepliesComments(repliesComments);

		return comment;
	}

	public Comment save(final Comment comment) {
		User principal;
		Date moment;
		Comment result;

		moment = new Date(System.currentTimeMillis() - 1);

		Assert.notNull(comment);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		comment.setMoment(moment);
		comment.setUser(principal);

		result = this.commentRepository.save(comment);

		return result;
	}

	public void delete(final Comment comment) {
		Admin principal;
		User user;
		Rende rende;
		Collection<Comment> comments, updated;

		principal = this.adminService.findByPrincipal();
		Assert.notNull(principal);

		//Eliminar relaciones

		for (final ReplyComment c : comment.getRepliesComments())
			this.replyCommentService.delete(c);

		user = comment.getUser();
		comments = user.getComments();
		updated = new ArrayList<Comment>(comments);
		updated.remove(comment);
		user.setComments(comments);

		rende = comment.getRende();
		comments = rende.getComments();
		updated = new ArrayList<Comment>(comments);
		updated.remove(comment);
		rende.setComments(updated);

		this.commentRepository.delete(comment);

	}
	public Collection<Comment> findByRendeId(final int rendeId) {
		Collection<Comment> rendes = new ArrayList<Comment>();
		rendes = this.commentRepository.findByRende(rendeId);
		return rendes;

	}

	public Comment findOne(final int commentId) {
		final Comment comment = this.commentRepository.findOne(commentId);
		return comment;

	}

	public Comment reconstruct(final CommentForm commentForm, final BindingResult binding) {
		Comment comment;
		if (commentForm.getId() == 0) {
			comment = this.create();
			comment.setUser(this.userService.findByPrincipal());
			comment.setPicture(commentForm.getPicture());

			final Rende rende = this.rendeService.findOne(commentForm.getRende());
			comment.setRende(rende);

			comment.setText(commentForm.getText());

		} else {
			comment = this.commentRepository.findOne(commentForm.getId());
			comment.setPicture(commentForm.getPicture());
			comment.setText(commentForm.getText());
			comment.setId(commentForm.getId());
			comment.setVersion(commentForm.getVersion());
		}

		this.validator.validate(commentForm, binding);
		return comment;
	}
}
