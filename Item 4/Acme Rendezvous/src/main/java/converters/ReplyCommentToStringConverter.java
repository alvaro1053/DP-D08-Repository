
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ReplyComment;

@Component
@Transactional
public class ReplyCommentToStringConverter implements Converter<ReplyComment, String> {

	@Override
	public String convert(final ReplyComment replyComment) {
		String result;

		if (replyComment == null)
			result = null;
		else
			result = String.valueOf(replyComment.getId());

		return result;
	}

}
