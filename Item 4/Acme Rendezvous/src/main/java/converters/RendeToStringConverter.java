package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Rende;



@Component
@Transactional
public class RendeToStringConverter implements Converter<Rende, String> {

	@Override
	public String convert(final Rende rende){
		String result;
		
		if(rende == null){
			result = null;
		}else{
			result = String.valueOf(rende.getId());
		}
		
		return result;
	}
	
	
}
