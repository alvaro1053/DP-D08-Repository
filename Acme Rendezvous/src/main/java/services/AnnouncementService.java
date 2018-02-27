
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Admin;
import domain.Announcement;
import domain.Rende;
import domain.User;

@Service
@Transactional
public class AnnouncementService {

	// Managed Repository
	@Autowired
	private AnnouncementRepository	announcementRepository;

	// Supporting services
	@Autowired
	private UserService				userService;

	@Autowired
	private AdminService			adminService;


	// Constructors

	public AnnouncementService() {
		super();
	}

	// Simple CRUD methods

	public Announcement create() {
		User principal;
		Announcement Announcement;
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		Announcement = new Announcement();
		return Announcement;
	}

	//  An actor who is not authenticated must be able to browse the list of Announcements and display them
	public Collection<Announcement> findAll() {
		final Collection<Announcement> result = this.announcementRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	// Other business methods

	public void delete(final Announcement announcement) {
		Admin principal;
		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId() != 0);

		principal = this.adminService.findByPrincipal();
		Assert.notNull(principal);

		final Rende rende = announcement.getRende();
		final Collection<Announcement> announcements = rende.getAnnouncements();
		final Collection<Announcement> updated = new ArrayList<Announcement>(announcements);
		updated.remove(announcement);
		rende.setAnnouncements(updated);

		this.announcementRepository.delete(announcement);
	}
	// Users must be able to create Announcements
	public Announcement save(final Announcement announcementToSave) {
		User principal;
		Announcement result;
		Assert.notNull(announcementToSave);

		principal = this.userService.findByPrincipal();

		Assert.notNull(principal);
		Assert.isTrue(principal.getRendes().contains(announcementToSave.getRende()));

		if (announcementToSave.getId() == 0) {
			final Date moment = new Date(System.currentTimeMillis() - 1);
			announcementToSave.setMoment(moment);

		}

		result = this.announcementRepository.save(announcementToSave);

		Collection<Announcement> announcements = new ArrayList<Announcement>();
		final Rende rende = result.getRende();
		announcements = rende.getAnnouncements();
		announcements.add(result);
		rende.setAnnouncements(announcements);

		return result;
	}
	public Collection<Announcement> findByRende(final Rende rende) {
		User principal;
		Collection<Announcement> result;
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		result = rende.getAnnouncements();

		return result;

	}

	public Announcement findOne(final int AnnouncementId) {
		Announcement result;

		result = this.announcementRepository.findOne(AnnouncementId);
		Assert.notNull(result);

		return result;

	}

	public Collection<Announcement> announcementsChronological(final int rendeId) {
		Collection<Announcement> result;

		result = this.announcementRepository.announcementsChronological(rendeId);

		return result;
	}

	public Collection<Announcement> announcementsChronologicalByUser(final int userId) {
		Collection<Announcement> result;
		
		Assert.notNull(userId);
		result = this.announcementRepository.announcementsChronologicalByUser(userId);

		return result;
	}

}
