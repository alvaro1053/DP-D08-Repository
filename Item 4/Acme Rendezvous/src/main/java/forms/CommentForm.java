
package forms;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class CommentForm extends DomainEntity {

	private String	text;
	private String	picture;
	private int		rende;


	public int getRende() {
		return this.rende;
	}

	public void setRende(final int rende) {
		this.rende = rende;
	}

	@NotBlank
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}
	@URL
	@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

}
