
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Rende;
import domain.ReplyComment;
import domain.ReplyQuestion;
import domain.User;

@Service
@Transactional
public class UserService {

	// Managed Repository
	@Autowired
	private UserRepository	UserRepository;


	// Supporting services

	// Constructors

	public UserService() {
		super();
	}

	// Simple CRUD methods
	public User create() {
		User result;

		result = new User();
		result.setrSVPS(new ArrayList<Rende>());
		result.setComments(new ArrayList<Comment>());
		result.setRendes(new ArrayList<Rende>());
		result.setRepliesComments(new ArrayList<ReplyComment>());
		result.setRepliesQuestions(new ArrayList<ReplyQuestion>());

		return result;
	}

	public User save(final User User) {
		User saved;
		Assert.notNull(User);

		if (User.getId() == 0) {
			final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
			User.getUserAccount().setPassword(passwordEncoder.encodePassword(User.getUserAccount().getPassword(), null));
		} else {
			User principal;
			principal = this.findByPrincipal();
			Assert.notNull(principal);

		}

		saved = this.UserRepository.save(User);

		return saved;
	}

	public User findOne(final int UserId) {
		User result;
		result = this.UserRepository.findOne(UserId);
		return result;
	}
	
	public Collection<User> findAll() {
		Collection<User> result;
		result = this.UserRepository.findAll();
		Assert.notNull(result);
		return result;

	}

	//Other business methods
	public User findByPrincipal() {
		User result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;

	}

	public User findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		User result;
		result = this.UserRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

}
