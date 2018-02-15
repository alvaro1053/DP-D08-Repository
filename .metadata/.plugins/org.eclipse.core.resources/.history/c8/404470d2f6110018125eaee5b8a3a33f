
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	private Date						moment;
	private String						text;
	private String						picture;
	private Rende						rende;
	private Collection<ReplyComment>	repliesComments;
	private User						user;


	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}
	@Valid
	@ManyToOne(optional = true)
	public Rende getRende() {
		return this.rende;
	}

	public void setRende(final Rende rende) {
		this.rende = rende;
	}
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<ReplyComment> getRepliesComments() {
		return this.repliesComments;
	}

	public void setRepliesComments(final Collection<ReplyComment> repliesComments) {
		this.repliesComments = repliesComments;
	}
	@Valid
	@ManyToOne(optional = true)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
