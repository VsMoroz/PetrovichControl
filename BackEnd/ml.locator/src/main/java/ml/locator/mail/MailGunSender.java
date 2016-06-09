package ml.locator.mail;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailGunSender implements EMail{
	private static final Logger LOGGER = Logger.getLogger(GmailSender.class);
	private static final String TRANSPORT_HOST = "https://api.mailgun.net/v3/sandboxce21ad4109424993b1908df5168de43e.mailgun.org/messages";

	@Override
	public EMailMessage sendMail(EMailMessage message) {
		ClientResponse clientResponse = send(message);
		String responseBody = clientResponse.getEntity(String.class); 
		EMailMessage response = new EMailMessage();
		
		try{
			response = EMail.Utills.getMessageFromJson(responseBody);
				
		} catch (JsonMappingException e){
			LOGGER.error(e.getMessage());
		} catch (JsonParseException e){
			LOGGER.error(e.getMessage());
		} catch (IOException e){
			LOGGER.error(e.getMessage());
		}
		response.setStatusMessage(EMailMessage.STATUS_OK);
		return response;
	}
	
	private ClientResponse send(EMailMessage message){
		
		final String APIKEY = System.getenv("MAILGUN_APIKEY");
		final String TRANSPORT_HOST = "https://api.mailgun.net/v3/" + System.getenv("MAILGUN_DOMAIN") + "/messages";
		
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", APIKEY));
		
		WebResource webResource = client.resource(TRANSPORT_HOST);
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add(MailAttribute.FROM, "iBeacon mailer <noreply@locator.ml>");
		
		List<String> addressList = message.getRecipientAddresses();
		
		if (!(addressList == null || addressList.isEmpty())) {
			formData.add(MailAttribute.TO, (addressList.get(0)));
			for (int i = 1; i < addressList.size(); i++) {
				formData.add(MailAttribute.CC,addressList.get(i));
			}
		}
		
		formData.add(MailAttribute.SUBJECT, "iBeacon notification");
		formData.add(MailAttribute.TEXT, message.getMessage());
		
		return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
	}
	
	private static class MailAttribute{
		private static final String FROM = "from";
		private static final String TO = "to";
		private static final String CC = "cc";
		private static final String BCC = "bcc";
		private static final String SUBJECT = "subject";
		private static final String TEXT = "text";	
	}
	

}
