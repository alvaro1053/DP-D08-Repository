
package forms;

import java.util.List;

import javax.validation.Valid;

import domain.DomainEntity;
import domain.Question;
import domain.ReplyQuestion;

public class ReplyQuestionForm extends DomainEntity {

	private List<ReplyQuestion>	replies;

	private List<Question>		questions;


	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(final List<Question> questions) {
		this.questions = questions;
	}

	@Valid
	public List<ReplyQuestion> getReplies() {
		return this.replies;
	}

	public void setReplies(final List<ReplyQuestion> replies) {
		this.replies = replies;
	}

}
