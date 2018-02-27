
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RendeRepository;
import domain.Admin;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rende;
import domain.ReplyComment;
import domain.ReplyQuestion;
import domain.User;
import forms.RendeForm;

@Service
@Transactional
public class RendeService {

	// Managed Repository
	@Autowired
	private RendeRepository			rendeRepository;

	// Supporting services
	@Autowired
	private UserService				userService;

	@Autowired
	private AdminService			adminService;

	@Autowired
	private CommentService			commentService;

	@Autowired
	private ReplyCommentService		replyCommentService;

	@Autowired
	private AnnouncementService		announcementService;

	@Autowired
	private QuestionService			questionService;

	@Autowired
	private ReplyQuestionService	replyQuestionService;


	// Constructors

	public RendeService() {
		super();
	}

	// Simple CRUD methods

	public RendeForm create() {
		User principal;
		RendeForm rendeForm;

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		rendeForm = new RendeForm();
		rendeForm.setIsDraft(true);

		return rendeForm;
	}

	//  An actor who is not authenticated must be able to browse the list of Rendes and display them
	public Collection<Rende> findAll() {
		final Collection<Rende> result = this.rendeRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	// Other business methods
	public void delete(final Rende rende) {
		User principal;
		Assert.notNull(rende);
		Assert.isTrue(rende.getId() != 0);

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);

		Assert.isTrue(this.findByUserId(principal.getId()).contains(rende));
		rende.setIsDeleted(true);

		this.rendeRepository.save(rende);
	}

	public void deleteByAdmin(final Rende rende) {
		Admin principal;

		Assert.notNull(rende);
		Assert.isTrue(rende.getId() != 0);
		final User user = rende.getUser();
		principal = this.adminService.findByPrincipal();
		Assert.notNull(principal);

		final Collection<ReplyQuestion> repliesQuestionsToRemove = this.replyQuestionService.selectByRendeId(rende.getId());

		final Collection<Comment> commentsToRemove = rende.getComments();

		final Collection<Question> questionsToRemove = rende.getQuestions();

		final Collection<ReplyComment> repliesToRemove = this.replyCommentService.selectByRendeId(rende.getId());

		final Collection<Announcement> announcementsToRemove = rende.getAnnouncements();

		final Collection<Rende> linkedsToOthers = this.selectLinkedById(rende.getId());

		for (final ReplyComment rC : repliesToRemove)
			this.replyCommentService.delete(rC);
		for (final Comment r : commentsToRemove)
			this.commentService.delete(r);
		for (final Announcement a : announcementsToRemove)
			this.announcementService.delete(a);
		for (final ReplyQuestion rQ : repliesQuestionsToRemove)
			this.replyQuestionService.delete(rQ);
		for (final Question q : questionsToRemove)
			this.questionService.delete(q);
		for (final Rende r : linkedsToOthers) {
			final Collection<Rende> rendesLinked = r.getLinked();
			final Collection<Rende> updated = new ArrayList<Rende>(rendesLinked);
			updated.remove(rende);
			r.setLinked(updated);
		}
		rende.setLinked(new ArrayList<Rende>());

		final Collection<Rende> userRendes = user.getRendes();
		final Collection<Rende> updated = new ArrayList<Rende>(userRendes);
		updated.remove(rende);
		user.setRendes(updated);

		for (final User u : rende.getAttendants()) {
			final Collection<Rende> userRZVPS = u.getrSVPS();
			final Collection<Rende> updated2 = new ArrayList<Rende>(userRZVPS);
			updated2.remove(rende);
			u.setrSVPS(updated2);
		}

		this.rendeRepository.delete(rende);

	}
	// Users must be able to create Rendes
	public Rende save(final Rende rendeToSave) {
		User principal;
		Rende result;
		Assert.notNull(rendeToSave);

		principal = this.userService.findByPrincipal();

		Assert.notNull(principal);

		result = this.rendeRepository.save(rendeToSave);

		if (rendeToSave.getId() == 0) {
			final Collection<User> attendants = new ArrayList<User>();
			attendants.add(principal);
			result.setUser(principal);
			result.setAttendants(attendants);

			final Collection<Rende> RSVPS = principal.getrSVPS();
			RSVPS.add(result);
			principal.setrSVPS(RSVPS);

		}

		return result;
	}
	public Collection<Rende> findByUserId(final int id) {
		User principal;
		Collection<Rende> result;

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		result = this.rendeRepository.selectByUserId(id);

		Assert.notNull(result);

		return result;

	}

	public Collection<Rende> findRSVPSByUser() {
		User principal;
		Collection<Rende> result;

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		result = principal.getrSVPS();

		Assert.notNull(result);

		return result;

	}

	public Rende findOne(final int RendeId) {
		Rende result;

		result = this.rendeRepository.findOne(RendeId);

		return result;

	}

	public Collection<Rende> selectNotAdultRendes() {
		Collection<Rende> result;
		result = this.rendeRepository.selectNotAdultRendes();
		return result;

	}

	public User rsvp(final Rende rende, final User user) {
		User result;

		if (!rende.getAttendants().contains(user)) {
			rende.getAttendants().add(user);
			user.getrSVPS().add(rende);
		}
		result = user;
		return result;
	}

	public User cancelRsvp(final Rende rende, final User user) {
		User result;

		if (rende.getAttendants().contains(user)) {
			rende.getAttendants().remove(user);
			user.getrSVPS().remove(rende);

			final Collection<ReplyQuestion> toDelete = this.replyQuestionService.selectByRendeAndUserId(rende.getId(), user.getId());
			for (final ReplyQuestion rQ : toDelete)
				this.replyQuestionService.delete(rQ);
		}

		result = user;
		return result;
	}


	//Reconstruct --------------------------------------------

	@Autowired
	private Validator	validator;


	public Rende reconstruct(final RendeForm rendeForm, final BindingResult binding) {
		Rende result = new Rende();
		User principal;
		principal = this.userService.findByPrincipal();
		final LocalDate now = new LocalDate();
		final LocalDate nacimiento = new LocalDate(principal.getDateBirth());
		final int años = Years.yearsBetween(nacimiento, now).getYears();
		final List<Question> questions = new ArrayList<Question>();

		if (rendeForm.getId() == 0) {
			result.setId(rendeForm.getId());
			result.setVersion(rendeForm.getVersion());
			result.setName(rendeForm.getName());
			result.setDescription(rendeForm.getDescription());
			result.setMoment(rendeForm.getMoment());
			result.setPicture(rendeForm.getPicture());
			result.setCoordenates(rendeForm.getCoordenates());
			result.setAttendants(rendeForm.getAttendants());

			if (años < 18 && rendeForm.getAdultOnly()) {
				result.setAdultOnly(false);
				binding.rejectValue("adultOnly", "rende.cannot.underage");
			} else
				result.setAdultOnly(rendeForm.getAdultOnly());

			if (rendeForm.getLinked() == null)
				result.setLinked(new ArrayList<Rende>());
			else if (!(rendeForm.getLinked().contains(null)))
				result.setLinked(rendeForm.getLinked());
			else {
				rendeForm.getLinked().remove(null);
				result.setLinked(rendeForm.getLinked());
			}

			if (rendeForm.getIsDraft() == null)
				result.setIsDraft(true);
			else
				result.setIsDraft(rendeForm.getIsDraft());

			result.setIsDeleted(false);
			result.setUser(principal);

			if (rendeForm.getQuestions() != null)
				for (final Question q : rendeForm.getQuestions())
					if (q.getQuestion() != null || q.getQuestion() != "" || q == null) {
						q.setRende(result);
						questions.add(q);
					}

			result.setQuestions(questions);

		} else if (rendeForm.getId() != 0 && rendeForm.getIsDraft() == null) {
			result = this.rendeRepository.findOne(rendeForm.getId());
			if (rendeForm.getLinked() == null) {
				final List<Rende> emptyLinked = new ArrayList<Rende>();
				result.setLinked(emptyLinked);
			} else
				result.setLinked(rendeForm.getLinked());

		} else {
			result = this.rendeRepository.findOne(rendeForm.getId());
			result.setName(rendeForm.getName());
			result.setDescription(rendeForm.getDescription());
			result.setMoment(rendeForm.getMoment());
			result.setPicture(rendeForm.getPicture());
			result.setCoordenates(rendeForm.getCoordenates());
			result.setIsDraft(rendeForm.getIsDraft());
			result.setLinked(rendeForm.getLinked());
			result.setIsDraft(rendeForm.getIsDraft());

			if (años < 18 && rendeForm.getAdultOnly()) {
				result.setAdultOnly(false);
				binding.rejectValue("adultOnly", "rende.cannot.underage");
			} else
				result.setAdultOnly(rendeForm.getAdultOnly());

			if (!(rendeForm.getQuestions() == null))
				result.setQuestions(rendeForm.getQuestions());

			if (!(rendeForm.getLinked() == null))
				result.setQuestions(rendeForm.getQuestions());

		}
		this.validator.validate(result, binding);
		return result;
	}

	public RendeForm reconstructForm(final Rende rende) {
		RendeForm result;

		result = new RendeForm();

		result.setId(rende.getId());
		result.setVersion(rende.getVersion());
		result.setName(rende.getName());
		result.setDescription(rende.getDescription());
		result.setMoment(rende.getMoment());
		result.setPicture(rende.getPicture());
		result.setCoordenates(rende.getCoordenates());
		result.setAttendants(rende.getAttendants());
		result.setAdultOnly(rende.getAdultOnly());
		result.setLinked(rende.getLinked());
		result.setIsDraft(rende.getIsDraft());
		result.setQuestions(rende.getQuestions());

		return result;
	}

	public Collection<Rende> selectLinkedById(final int rendeId) {
		final Collection<Rende> rende = this.rendeRepository.selectLinkedById(rendeId);
		return rende;
	}

}
