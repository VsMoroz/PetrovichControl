package ml.locator.mail;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface EMail {

	EMailMessage sendMail(EMailMessage message);
	
	static class Utills{
		public static EMailMessage getMessageFromJson(String json) throws IOException, JsonParseException, JsonMappingException{
			ObjectMapper mapper = new ObjectMapper();
			EMailMessage message = mapper.readValue(json, EMailMessage.class);
			return message;
		}
	}

}
