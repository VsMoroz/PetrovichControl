package ml.locator.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

public class GmailSender implements EMail {
	private static final Logger LOGGER = Logger.getLogger(GmailSender.class);
	private static final String TRANSPORT = "smtp";
	private static final String TRANSPORT_HOST = "smtp.gmail.com";
	private static final String CONTENT_TYPE = "text/html";
	private static final String ADDRESS_FROM = "aleks.iakovenko@gmail.com";

	private static final int SMTP_PORT = 587;

	private Properties mailServerProperties;

	public GmailSender() {
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", SMTP_PORT);
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
	}

	@Override
	public EMailMessage sendMail(EMailMessage message){
		Transport transport = null;
		try {
			Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			MimeMessage mailMessage = new MimeMessage(getMailSession);

			List<String> addressList = message.getRecipientAddresses();

			if (!(addressList == null || addressList.isEmpty())) {
				mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(addressList.get(0)));
				for (int i = 1; i < addressList.size(); i++) {
					mailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(addressList.get(i)));
				}
			}
			mailMessage.setSubject("iBeacon locator");
			String emailBody = message.getMessage();
			mailMessage.setContent(emailBody, CONTENT_TYPE);

			transport = getMailSession.getTransport(TRANSPORT);
			
			try{
				deliverMessage(transport, mailMessage);
			} catch (MessagingException e){
				throw e;
			} finally {
				if (transport != null) {
					transport.close();
				}
			}
			
		} catch (AddressException e) {
			LOGGER.error(e.getMessage());
			message.setStatusMessage(EMailMessage.STATUS_FAIL + ": " + e.getMessage());
			return message;
			
		} catch (MessagingException e) {
			LOGGER.error(e.getMessage());
			message.setStatusMessage(EMailMessage.STATUS_FAIL + ": " + e.getMessage());
			return message;
		} 

		message.setStatusMessage(EMailMessage.STATUS_OK);
		return message;
	}
	private void deliverMessage(Transport transport, MimeMessage mailMessage) throws MessagingException{
		// TODO It will be needed to implement a password encryption
		transport.connect(TRANSPORT_HOST, ADDRESS_FROM, "(C?0F{\"ZjzCk");
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
	}

}
