
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuestionRepository;
import domain.Actor;
import domain.Admin;
import domain.Question;
import domain.Rende;
import domain.ReplyQuestion;
import domain.User;
import forms.ReplyQuestionForm;

@Service
@Transactional
public class QuestionService {

	// Managed Repository
	@Autowired
	private QuestionRepository		questionRepository;

	// Supporting Repository
	@Autowired
	private UserService				userService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ReplyQuestionService	replyQuestionService;

	@Autowired
	private Validator				validatorService;


	// Constructors
	public QuestionService() {
		super();
	}

	// Simple CRUD methods
	public Question create() {
		User principal;
		Question question;
		final Collection<ReplyQuestion> repliesQuestions = new ArrayList<ReplyQuestion>();
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		question = new Question();
		question.setReplyQuestions(repliesQuestions);

		return question;
	}

	public Question save(final Question question) {
		User principal;
		Question result;

		Assert.notNull(question);

		principal = this.userService.findByPrincipal();

		Assert.notNull(principal);

		result = this.questionRepository.save(question);

		return result;
	}

	public void delete(final Question question) {
		Assert.notNull(question);
		List<Question> updated;
		final Actor principal = this.actorService.findByPrincipal();
		if (principal instanceof User)
			Assert.isTrue(((User) principal).getRendes().contains(question.getRende()) || principal instanceof Admin);

		for (final ReplyQuestion rc : question.getReplyQuestions())
			this.replyQuestionService.delete(rc);

		final Rende rende = question.getRende();
		final List<Question> questions = rende.getQuestions();
		updated = new ArrayList<Question>(questions);
		updated.remove(question);
		rende.setQuestions(updated);

		this.questionRepository.delete(question);
	}
	public Collection<Question> SelectRepliedByUserId(final int id) {
		final User principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		final Collection<Question> res = this.questionRepository.repliedByUserId(id);
		return res;
	}

	public Question findOne(final int id) {
		final Actor principal = this.actorService.findByPrincipal();
		final Question res = this.questionRepository.findOne(id);
		if (principal instanceof User)
			Assert.isTrue(((User) principal).getRendes().contains(res.getRende()) || ((User) principal).getrSVPS().contains(res.getRende()) || principal instanceof Admin);
		return res;
	}

	public List<ReplyQuestion> reconstruct(final ReplyQuestionForm replyQuestionForm, final BindingResult binding) {
		final User principal = this.userService.findByPrincipal();
		List<ReplyQuestion> replies = new ArrayList<ReplyQuestion>();
		if (replyQuestionForm.getId() == 0) {
			final List<Question> questions = replyQuestionForm.getQuestions();
			replies = replyQuestionForm.getReplies();

			for (int i = 0; i < questions.size(); i++) {
				replies.get(i).setQuestion(questions.get(i));
				replies.get(i).setUser(principal);
				this.validatorService.validate(replies.get(i), binding);

			}

		}

		return replies;
	}
}
