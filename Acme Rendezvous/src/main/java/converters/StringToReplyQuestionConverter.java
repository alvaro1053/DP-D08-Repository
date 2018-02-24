
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import repositories.ReplyQuestionRepository;
import domain.ReplyQuestion;

@Component
@Transactional
public class StringToReplyQuestionConverter implements Converter<String, ReplyQuestion> {

	@Autowired
	ReplyQuestionRepository	replyQuestionRepository;


	@Override
	public ReplyQuestion convert(final String text) {
		ReplyQuestion result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.replyQuestionRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
