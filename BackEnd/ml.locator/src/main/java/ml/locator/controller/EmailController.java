package ml.locator.controller;



import org.jboss.logging.Logger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.locator.mail.EMailMessage;
import ml.locator.mail.EMailSender;
import ml.locator.mail.GmailSender;

@RestController
@RequestMapping("mail")
public class EmailController {
	private static final Logger LOGGER = Logger.getLogger(EmailController.class); 

	@RequestMapping(value = "send", method = RequestMethod.GET)
	public EMailMessage send(@RequestParam String address, @RequestParam String message) {

			EMailMessage messageToSend = new EMailMessage();
			messageToSend.setMessage(message);
			messageToSend.getRecipientAddresses().add(address);
			
			EMailSender sender = new EMailSender();
			sender.setSender(new GmailSender());
			messageToSend = sender.sendEMail(messageToSend);
			
		return messageToSend;

	}

	

}
