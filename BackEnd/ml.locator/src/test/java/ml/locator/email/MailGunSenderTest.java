package ml.locator.email;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.locator.mail.EMailMessage;
import ml.locator.mail.EMailSender;
import ml.locator.mail.MailGunSender;

public class MailGunSenderTest {
	private static final String[] ADDRESS = { "11yaan@ukr.net", "aleks.iakovenko@gmail.com" };

	@Test
	public void testSendMail() {

		EMailMessage messageToSend = new EMailMessage();
		messageToSend.setMessage("ibeacon JUnit testSendMail()");
		for (String address : ADDRESS) {
			messageToSend.getRecipientAddresses().add(address);
		}

		EMailSender sender = new EMailSender();
		sender.setSender(new MailGunSender());
		messageToSend = sender.sendEMail(messageToSend);
		assertEquals(EMailMessage.STATUS_OK, messageToSend.getStatusMessage());
		assertEquals("Queued. Thank you.", messageToSend.getMessage());
	}

}
