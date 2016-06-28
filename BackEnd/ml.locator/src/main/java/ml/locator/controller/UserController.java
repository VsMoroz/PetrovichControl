package ml.locator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ml.locator.mail.EMailMessage;
import ml.locator.mail.EMailSender;
import ml.locator.mail.MailGunSender;
import ml.locator.model.TokenDTO;
import ml.locator.model.UserDTO;
import ml.locator.model.entity.Role;
import ml.locator.model.entity.User;
import ml.locator.model.service.role.RoleDAO;
import ml.locator.model.service.user.UserDAO;
import ml.locator.security.TokenUtils;
import ml.locator.utils.CryptCodeGenerator;
import ml.locator.utils.PrincipalInformation;
import ml.locator.utils.registration.PreRegisterCache;
import ml.locator.utils.registration.RegisterLink;
import ml.locator.utils.registration.RegisterLinkGenerator;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_EMAIL = "email";
	 
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PrincipalInformation principalInformation;
	
	@Autowired
	private UserDAO<User, String> userDAO;
	
	@Autowired
	private RoleDAO<Role, Integer> roleDAO;
	
	@Autowired
	private CryptCodeGenerator cryptCodeGenerator;
	
	@Autowired
	private PreRegisterCache preRegisterCache;
	
	@RequestMapping(value="auth-info", method = RequestMethod.GET)
	public UserDTO getPrinsipal(){
		String principalName = principalInformation.getPrincipalName();
		
		User entity = userDAO.findByName(principalName);
		UserDTO user = new UserDTO();
		user.setUsername(entity.getUsername());
		user.setEmail(entity.getEmail());
			
		return user;
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public Response register(
			@RequestParam(PARAM_USERNAME) String username,
			@RequestParam(PARAM_EMAIL) String email,
			@RequestParam(PARAM_PASSWORD) String password,
			@RequestParam(value="userRoles[]") String[] userRoles ){
		
		User persistingUser = new User();
		persistingUser.setUsername(username);
		persistingUser.setEmail(email);
		persistingUser.setPassword(cryptCodeGenerator.encode(password));
		persistingUser.setEnabled(false);
		
		UserDTO newUser = null;
		List<Role> roles = new ArrayList<>();
		List<String> paramRoles = Arrays.asList(userRoles); 
		for(String userRole : paramRoles){
			Role existedRole = roleDAO.findByName(userRole);
			if(existedRole != null){
				roles.add(existedRole);
				persistingUser.setRoles(roles);	
			}
		}
		
		sendConfirmationRequect(userDAO.save(persistingUser));
				
		return Response.ok().build();
	}	
	
	@RequestMapping(value = "confirm", method = RequestMethod.GET)
	public Response confirm(
			@RequestParam(PARAM_EMAIL) String email,
			@RequestParam("key") String key){
		if(!preRegisterCache.isEmpty()){
			String login = preRegisterCache.getLogin(key);
			User user = userDAO.findByName(login);
			user.setEnabled(true);
			
			userDAO.save(user);
			return Response.ok().build();
		}
		
		return Response.notModified().build();
	}
	
	@RequestMapping(value="authenticate", method=RequestMethod.POST)
	public TokenDTO authenticate(@RequestParam(PARAM_USERNAME) String username,
			@RequestParam(PARAM_PASSWORD) String password){
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = userDAO.loadUserByUsername(username);
		TokenDTO token = new TokenDTO(TokenUtils.createToken(userDetails));
		return token;
	}
	
	private void sendConfirmationRequect(User user){
		RegisterLink link = new RegisterLinkGenerator(getLocalHost(), user.getEmail()).generateLink();
		preRegisterCache.put(user.getUsername(), link);
		
		String message = "Confirm registartion: " + link.toString();
		
		EMailMessage messageToSend = new EMailMessage();
		messageToSend.setMessage(message);
		messageToSend.getRecipientAddresses().add(user.getEmail());
		
		EMailSender sender = new EMailSender();
		sender.setSender(new MailGunSender());
		messageToSend = sender.sendEMail(messageToSend);
	}
	
	private String getLocalHost(){
		final String OPENSHIFT_GEAR_NAME = System.getenv("OPENSHIFT_GEAR_NAME");
		if (OPENSHIFT_GEAR_NAME == null){
			return "http://localhost:8080/process/api/user/confirm";
		} 
		if (OPENSHIFT_GEAR_NAME.equals("ibeacon")){
			return "http://locator.ml/process/api/user/confirm";
		} 
		if (OPENSHIFT_GEAR_NAME.equals("test")){
			return "http://test-ikcy.rhcloud.com/process/api/user/confirm";
		} 
		return null;
		
	}
	
	
	

}
