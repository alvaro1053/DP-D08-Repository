
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ReplyCommentRepository;
import domain.ReplyComment;

@Component
@Transactional
public class StringToReplyCommentConverter implements Converter<String, ReplyComment> {

	@Autowired
	ReplyCommentRepository	replyCommentRepository;


	@Override
	public ReplyComment convert(final String text) {
		ReplyComment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.replyCommentRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
