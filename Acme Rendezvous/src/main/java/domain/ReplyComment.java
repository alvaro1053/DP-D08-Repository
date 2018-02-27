
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class ReplyComment extends DomainEntity {

	private String	reply;
	private User	user;
	private Comment	comment;


	@NotNull
	@Valid
	@ManyToOne(optional = true)
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(final Comment comment) {
		this.comment = comment;
	}

	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
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
