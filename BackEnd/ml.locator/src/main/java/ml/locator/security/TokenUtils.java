/**
 * 
 */
package ml.locator.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

/**
 * Jun 27, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
public class TokenUtils {
	
//	private static final String IBEACON_TOKEN_KEY = System.getProperty("IBEACON_TOKEN_KEY");
	private static final String IBEACON_TOKEN_KEY = "dixi-pixi";
	
	public static String createToken(UserDetails userDetails){
		
		//Expires in one hour
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(computeSignature(userDetails, expires));
		return tokenBuilder.toString();
	}
	
	public static String computeSignature(UserDetails userDetails, long expires){
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(IBEACON_TOKEN_KEY);
		
		MessageDigest messageDigest;
		try{
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e){
			throw new IllegalStateException("No such algorithm available!");
		}
		return new String(Hex.encode(messageDigest.digest(signatureBuilder.toString().getBytes())));
	}
	
	public static String getUserNameFromToken(String authToken){
		if(authToken != null){
			String[] parts = authToken.split(":");
			return parts[0];
		} else {
			return null;
		}
	}
	
	public static boolean isTokenValid(String authToken, UserDetails userDetails){
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];
		if(expires < System.currentTimeMillis()){
			return false;
		}
		return signature.equals(computeSignature(userDetails, expires));
	}

}
