
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class ReplyQuestion extends DomainEntity {

	private String	reply;
	private User	user;


	@NotBlank
	public String getReply() {
		return this.reply;
	}

	public void setReply(final String reply) {
		this.reply = reply;
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
