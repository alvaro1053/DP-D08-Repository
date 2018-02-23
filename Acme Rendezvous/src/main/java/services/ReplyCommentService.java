
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReplyCommentRepository;
import domain.Admin;
import domain.Comment;
import domain.ReplyComment;
import domain.User;

@Service
@Transactional
public class ReplyCommentService {

	// Managed Repository
	@Autowired
	private ReplyCommentRepository	replyCommentRepository;

	// Managed services
	@Autowired
	private UserService				userService;

	@Autowired
	private AdminService			adminService;


	//Constructor
	public ReplyCommentService() {
		super();
	}

	public ReplyComment create() {
		User principal;
		ReplyComment replyComment;
		//		Date moment;//no deberíamos guardar ek momento de la respuesta

		//		moment = new Date(System.currentTimeMillis() - 1);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		replyComment = new ReplyComment();

		replyComment.setUser(principal);
		//		replyComment.setMoment(moment);

		return replyComment;
	}

	public ReplyComment save(final ReplyComment replyComment) {
		User principal;
		//		Date moment;
		ReplyComment result;
		Collection<ReplyComment> replyComments, updated;

		//		moment = new Date(System.currentTimeMillis() - 1);

		Assert.notNull(replyComment);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		//		replyComment.setMoment(moment);
		replyComment.setUser(principal);

		result = this.replyCommentRepository.save(replyComment);

		//Actualizar relaciones
		replyComments = principal.getRepliesComments();
		updated = new ArrayList<ReplyComment>(replyComments);
		updated.add(result);
		principal.setRepliesComments(updated);

		final Comment comment = result.getComment();
		final Collection<ReplyComment> updated2 = new ArrayList<ReplyComment>(comment.getRepliesComments());
		updated2.add(result);
		comment.setRepliesComments(updated2);

		return result;
	}

	public void delete(final ReplyComment replyComment) {
		Admin principal;
		User user;
		Collection<ReplyComment> replycomments, updated, updated2;

		principal = this.adminService.findByPrincipal();
		Assert.notNull(principal);

		//Eliminar relaciones
		user = replyComment.getUser();
		replycomments = user.getRepliesComments();
		updated = new ArrayList<ReplyComment>(replycomments);
		updated.remove(replyComment);
		user.setRepliesComments(updated);

		final Comment c = replyComment.getComment();
		updated2 = new ArrayList<ReplyComment>(c.getRepliesComments());
		updated2.remove(replyComment);
		c.setRepliesComments(updated2);

		this.replyCommentRepository.delete(replyComment);

	}
	public Collection<ReplyComment> selectByRendeId(final int rendeId) {
		final Collection<ReplyComment> res = this.replyCommentRepository.selectByRendeId(rendeId);
		return res;
	}

	public ReplyComment findOne(final int replyCommentId) {
		final ReplyComment replyComment = this.replyCommentRepository.findOne(replyCommentId);
		Assert.notNull(replyComment);
		return replyComment;
	}
}
