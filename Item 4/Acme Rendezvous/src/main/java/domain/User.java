
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	private Collection<Comment>			comments;
	private Collection<ReplyComment>	repliesComments;
	private Collection<ReplyQuestion>	repliesQuestions;
	private Collection<Rende>			rendes;
	private Collection<Rende>			rSVPS;


	@ManyToMany
	public Collection<Rende> getrSVPS() {
		return this.rSVPS;
	}

	public void setrSVPS(final Collection<Rende> rSVPS) {
		this.rSVPS = rSVPS;
	}

	@OneToMany(mappedBy = "user")
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
	@OneToMany(mappedBy = "user")
	public Collection<ReplyComment> getRepliesComments() {
		return this.repliesComments;
	}

	public void setRepliesComments(final Collection<ReplyComment> repliesComments) {
		this.repliesComments = repliesComments;
	}
	@OneToMany(mappedBy = "user")
	public Collection<ReplyQuestion> getRepliesQuestions() {
		return this.repliesQuestions;
	}

	public void setRepliesQuestions(final Collection<ReplyQuestion> repliesQuestions) {
		this.repliesQuestions = repliesQuestions;
	}
	@OneToMany(mappedBy = "user")
	public Collection<Rende> getRendes() {
		return this.rendes;
	}

	public void setRendes(final Collection<Rende> rendes) {
		this.rendes = rendes;
	}

}
