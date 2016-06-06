package ml.locator.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CryptCodeGenerator {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	 
	public String encode(String value){
		
		String result;
		encoder = new BCryptPasswordEncoder(); 
		result = encoder.encode(value); 
		
		return result;
	}
	
	public void decode(String value){
		
		
	}
	
	public static void main(String[] args) {

	        int i = 0;
	        while (i < 10) {
	            String password = args[0];
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            String hashedPassword = passwordEncoder.encode(password);

	            System.out.println(hashedPassword);
	            i++;
	        }

	 }
	 

}
