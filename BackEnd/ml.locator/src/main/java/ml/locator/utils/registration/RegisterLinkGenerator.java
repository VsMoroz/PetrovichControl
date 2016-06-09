/**
 * 
 */
package ml.locator.utils.registration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jun 7, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
public class RegisterLinkGenerator {
	
	private String host;
	private String email;
	
	public RegisterLinkGenerator(String host, String email){
		this.host = host;
		this.email = email;
	}
	
	public RegisterLink generateLink(){
		String key = generateKey();	 
		return  new RegisterLink(host, email, key);
	}
	
	public String generateKey(){
		final int LENGTH = 16;
		byte[] val = email.getBytes();
		
		StringBuilder str = new StringBuilder();
		for (byte v : val){
			str.append(v);
		}
		long emailAsBytes = Long.parseLong(str.substring(0, LENGTH + 1));
		long key = new Date().getTime();
		key = key ^ emailAsBytes ; 
		 
		return String.valueOf(key > 0 ? key : key * -1 ); 
	}
}
