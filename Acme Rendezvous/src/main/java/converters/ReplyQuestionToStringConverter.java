
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ReplyQuestion;

@Component
@Transactional
public class ReplyQuestionToStringConverter implements Converter<ReplyQuestion, String> {

	@Override
	public String convert(final ReplyQuestion replyQuestion) {
		String result;

		if (replyQuestion == null)
			result = null;
		else
			result = String.valueOf(replyQuestion.getId());

		return result;
	}

}
