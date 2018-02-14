
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {

	private String			question;
	private ReplyQuestion	replyQuestion;


	@NotBlank
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(final String question) {
		this.question = question;
	}
	@OneToOne(cascade = CascadeType.ALL)
	public ReplyQuestion getReplyQuestion() {
		return this.replyQuestion;
	}

	public void setReplyQuestion(final ReplyQuestion replyQuestion) {
		this.replyQuestion = replyQuestion;
	}

}
