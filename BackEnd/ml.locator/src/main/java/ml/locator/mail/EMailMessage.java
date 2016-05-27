package ml.locator.mail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EMailMessage implements Serializable {
	private static final long serialVersionUID = -1523004349250805728L;
	
	public static final String STATUS_OK = "Sent";
	public static final String STATUS_FAIL = "Sending failed";
	private String id;
	private List<String> recipientAddresses;
	private String message; 
	private String statusMessage;
	
	public EMailMessage(){
		recipientAddresses = new ArrayList<String>();
	}

	public EMailMessage(String id, List<String> recipientAddresses, String message, String statusMessage) {
		this.id = id;
		this.recipientAddresses = recipientAddresses;
		this.message = message;
		this.statusMessage = statusMessage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getRecipientAddresses() {
		return recipientAddresses;
	}

	public void setRecipientAddresses(List<String> recipientAddresses) {
		this.recipientAddresses = recipientAddresses;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

		
	

}
