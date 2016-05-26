package ml.locator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ml.locator.model.UserDTO;
import ml.locator.model.entity.User;
import ml.locator.model.service.user.UserDAO;
import ml.locator.utils.PrincipalInformation;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private PrincipalInformation principalInformation;
	
	@Autowired
	private UserDAO<User, String> userDAO;
	
	@RequestMapping(value="auth-info", method = RequestMethod.GET)
	public UserDTO getPrinsipal(){
		String principalName = principalInformation.getPrincipalName();
		
		User entity = userDAO.findByName(principalName);
		UserDTO user = new UserDTO();
		user.setUsername(entity.getUsername());
		user.setEmail(entity.getEmail());
		
		return user;
	}

}
