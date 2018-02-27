
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReplyQuestionRepository;
import domain.Question;
import domain.ReplyQuestion;
import domain.User;

@Service
@Transactional
public class ReplyQuestionService {

	// Managed Repository
	@Autowired
	private ReplyQuestionRepository	replyQuestionRepository;

	// Supporting Repository
	@Autowired
	private UserService				userService;


	// Constructors
	public ReplyQuestionService() {
		super();
	}

	// Simple CRUD methods
	public ReplyQuestion create() {
		User principal;
		ReplyQuestion replyQuestion;
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		replyQuestion = new ReplyQuestion();

		return replyQuestion;
	}

	public ReplyQuestion save(final ReplyQuestion replyQuestion) {
		User principal;
		ReplyQuestion result;
		Assert.notNull(replyQuestion);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		replyQuestion.setUser(principal);

		result = this.replyQuestionRepository.save(replyQuestion);
		final Collection<ReplyQuestion> update = principal.getRepliesQuestions();
		update.add(result);
		principal.setRepliesQuestions(update);

		final Question question = result.getQuestion();
		final Collection<ReplyQuestion> update2 = question.getReplyQuestions();
		update2.add(result);
		question.setReplyQuestions(update2);

		return result;
	}

	public void delete(final ReplyQuestion replyQuestion) {
		Collection<ReplyQuestion> updated, updated2;
		Assert.notNull(replyQuestion);

		final Question question = replyQuestion.getQuestion();
		final Collection<ReplyQuestion> replyQuestions1 = question.getReplyQuestions();
		updated = new ArrayList<ReplyQuestion>(replyQuestions1);
		updated.remove(replyQuestion);
		question.setReplyQuestions(updated);

		final User user = replyQuestion.getUser();
		final Collection<ReplyQuestion> replyQuestions2 = user.getRepliesQuestions();
		updated2 = new ArrayList<ReplyQuestion>(replyQuestions2);
		updated2.remove(replyQuestion);
		user.setRepliesQuestions(updated2);

		this.replyQuestionRepository.delete(replyQuestion);

	}
	public Collection<ReplyQuestion> selectByRendeId(final int rendeId) {
		final Collection<ReplyQuestion> res = this.replyQuestionRepository.selectByRendeId(rendeId);
		return res;
	}

	public Collection<ReplyQuestion> selectByRendeAndUserId(final int rendeId, final int userId) {
		final Collection<ReplyQuestion> res = this.replyQuestionRepository.selectByRendeAndUserId(rendeId, userId);
		return res;
	}

}
