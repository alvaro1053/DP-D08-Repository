
package forms;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import domain.DomainEntity;
import domain.Location;
import domain.Question;
import domain.Rende;
import domain.User;

public class RendeForm extends DomainEntity {

	private String				name;
	private String				description;
	private Date				moment;
	private String				picture;
	private Location			coordenates;
	private Collection<User>	attendants;
	private Boolean				isDraft;
	private Boolean				adultOnly;
	private Collection<Rende>	linked;

	private List<Question>		questions;

	public Boolean				isDeleted;


	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final List<Question> questions) {
		this.questions = questions;
	}

	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(final Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public RendeForm() {
		super();
	}

	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}
	
	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getPicture() {
		return this.picture;
	}
	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public Location getCoordenates() {
		return this.coordenates;
	}
	public void setCoordenates(final Location coordenates) {
		this.coordenates = coordenates;
	}

	public Collection<User> getAttendants() {
		return this.attendants;
	}
	public void setAttendants(final Collection<User> attendants) {
		this.attendants = attendants;
	}

	public Boolean getIsDraft() {
		return this.isDraft;
	}
	public void setIsDraft(final Boolean isDraft) {
		this.isDraft = isDraft;
	}

	public Boolean getAdultOnly() {
		return this.adultOnly;
	}
	public void setAdultOnly(final Boolean adultOnly) {
		this.adultOnly = adultOnly;
	}

	public Collection<Rende> getLinked() {
		return this.linked;
	}
	public void setLinked(final Collection<Rende> linked) {
		this.linked = linked;
	}

}
