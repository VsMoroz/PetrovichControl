package ml.locator.mail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class EMailSender {
	private EMail sender;
	
	public void setSender(EMail sender){
		this.sender = sender;
	}
	
	public EMailMessage sendEMail(EMailMessage message){				
		return sender.sendMail(message);
	}
}
