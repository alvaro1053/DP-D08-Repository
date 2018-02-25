
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "adultOnly")})
public class Rende extends DomainEntity {

	private String						name;
	private String						description;
	private Date						moment;
	private String						picture;
	private Location					coordenates;
	private Collection<User>			attendants;
	private Boolean						isDraft;
	private Boolean						adultOnly;
	private Boolean						isDeleted;
	private Collection<Rende>			linked;
	private Collection<Announcement>	announcements;
	private Collection<Comment>			comments;
	private List<Question>				questions;
	private User						user;


	@NotNull
	@Valid
	@ManyToOne(optional = true)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Future
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}
	@Valid
	public Location getCoordenates() {
		return this.coordenates;
	}

	public void setCoordenates(final Location coordenates) {
		this.coordenates = coordenates;
	}
	@ManyToMany
	public Collection<User> getAttendants() {
		return this.attendants;
	}

	public void setAttendants(final Collection<User> attendants) {
		this.attendants = attendants;
	}
	@NotNull
	public Boolean getIsDraft() {
		return this.isDraft;
	}

	public void setIsDraft(final Boolean isDraft) {
		this.isDraft = isDraft;
	}
	@NotNull
	public Boolean getAdultOnly() {
		return this.adultOnly;
	}

	public void setAdultOnly(final Boolean adultOnly) {
		this.adultOnly = adultOnly;
	}
	@NotNull
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(final Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToMany
	public Collection<Rende> getLinked() {
		return this.linked;
	}

	public void setLinked(final Collection<Rende> linked) {
		this.linked = linked;
	}
	@OneToMany(mappedBy = "rende")
	public Collection<Announcement> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(final Collection<Announcement> announcements) {
		this.announcements = announcements;
	}
	@OneToMany(mappedBy = "rende")
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@ElementCollection
	@Valid
	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final List<Question> questions) {
		this.questions = questions;
	}

}
