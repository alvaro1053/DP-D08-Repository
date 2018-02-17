
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendeRepository;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rende;
import domain.User;

@Service
@Transactional
public class RendeService {

	// Managed Repository
	@Autowired
	private RendeRepository	rendeRepository;

	// Supporting services
	@Autowired
	private UserService		userService;


	// Constructors

	public RendeService() {
		super();
	}

	// Simple CRUD methods

	public Rende create() {
		User principal;
		Rende Rende;
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		Rende = new Rende();
		Rende.setAnnouncements(new ArrayList<Announcement>());
		Rende.setComments(new ArrayList<Comment>());
		Rende.setQuestions(new ArrayList<Question>());
		Rende.setUser(principal);
		Rende.setIsDraft(true);
		Rende.setIsDeleted(false);
		return Rende;
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

		Assert.isTrue(this.findByUser().contains(rende));
		Assert.isTrue(rende.getIsDraft());
		rende.setIsDeleted(true);

		this.rendeRepository.save(rende);
	}

	// Users must be able to create Rendes
	public Rende save(final Rende rendeToSave) {
		User principal;
		Rende result;
		Collection<Rende> Rendes, updated, rSVPS, updated2;
		Assert.notNull(rendeToSave);

		principal = this.userService.findByPrincipal();

		Assert.notNull(principal);

		if (rendeToSave.getId() == 0) {
			final Collection<User> attendants = new ArrayList<User>();
			attendants.add(principal);
			rendeToSave.setUser(principal);
			rendeToSave.setAttendants(attendants);
		}

		final Date currentMoment = new Date(System.currentTimeMillis() - 1);

		Assert.isTrue(rendeToSave.getMoment().after(currentMoment));
		
		result = this.rendeRepository.save(rendeToSave);

		Rendes = principal.getRendes();
		updated = new ArrayList<Rende>(Rendes);
		updated.add(result);
		principal.setRendes(updated);

		rSVPS = principal.getrSVPS();
		updated2 = new ArrayList<Rende>(rSVPS);
		updated2.add(result);
		principal.setrSVPS(updated2);

		return result;
	}
	public Collection<Rende> findByUser() {
		User principal;
		Collection<Rende> result;

		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		result = principal.getRendes();

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
		Assert.notNull(result);

		return result;

	}

}
