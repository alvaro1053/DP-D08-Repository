
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReplyQuestionRepository;
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
	private UserService			userService;


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

		result = this.replyQuestionRepository.save(replyQuestion);

		return result;
	}

	public void delete(final ReplyQuestion replyQuestion) {
		User principal;
		Assert.notNull(replyQuestion);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		this.replyQuestionRepository.delete(replyQuestion);
		
	}

}
