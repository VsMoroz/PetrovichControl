package ml.locator.model.service.user;


import javax.ejb.Local;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import ml.locator.model.service.DAO;

@Local
public interface UserDAO<T, I> extends DAO<T, I>, UserDetailsService {
	
	  UserDetails loadUserByUsername(String username);
	  T findByName(String name);

}
