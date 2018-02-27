
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "question")})
public class Question extends DomainEntity {

	private String						question;
	private Collection<ReplyQuestion>	replyQuestions;
	private Rende						rende;


	@Valid
	@ManyToOne(optional = true)
	public Rende getRende() {
		return this.rende;
	}

	public void setRende(final Rende rende) {
		this.rende = rende;
	}

	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(final String question) {
		this.question = question;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<ReplyQuestion> getReplyQuestions() {
		return this.replyQuestions;
	}

	public void setReplyQuestions(final Collection<ReplyQuestion> replyQuestions) {
		this.replyQuestions = replyQuestions;
	}

}
