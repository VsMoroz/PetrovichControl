package ml.locator.model.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import ml.locator.model.entity.User;
import ml.locator.model.service.DAO;

public interface UserDAO<T, I> extends DAO<User,String>, UserDetailsService {
	
	  UserDetails loadUserByUsername(String username);
	  User findByName(String name);

}
