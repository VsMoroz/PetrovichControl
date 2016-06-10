/**
 * 
 */
package ml.locator.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Jun 9, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		super.onAuthenticationFailure(request, response, exception);
		if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)){
			LOGGER.info("Unregistered user");
		} else if (exception.getClass().isAssignableFrom(DisabledException.class)){
			LOGGER.info("User is disabled");
		}
		
	}
	
	
	
	

}
