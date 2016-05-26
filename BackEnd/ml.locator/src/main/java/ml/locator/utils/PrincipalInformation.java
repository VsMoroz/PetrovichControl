package ml.locator.utils;

import javax.ws.rs.WebApplicationException;

import org.jboss.logging.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PrincipalInformation {
	private static final Logger LOGGER = Logger.getLogger(PrincipalInformation.class);
	private static final String NOT_AUTHORIZED_MESSAGE = "User has not been authorized!";

	public String getPrincipalName() {

		String principalName;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				principalName = ((UserDetails) principal).getUsername();

			} else {
				principalName = principal.toString();
			}
			if (principalName.equals("anonymousUser")) {
				LOGGER.debug(NOT_AUTHORIZED_MESSAGE);
				throw new WebApplicationException(401);
			}
		} catch (NullPointerException e) {
			LOGGER.info(NOT_AUTHORIZED_MESSAGE);
			throw new WebApplicationException(401);
		}
		return principalName;
	}

}
