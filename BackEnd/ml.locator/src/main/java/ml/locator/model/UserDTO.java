package ml.locator.model;

import java.util.HashMap;
import java.util.Map;

import ml.locator.model.entity.User;

public class UserDTO {
	private String username;
	private String email;
	private String password;
	private Map<String, Boolean> roles = new HashMap<String, Boolean>();

	public UserDTO(){}
	public UserDTO (String username, String email, String password){
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public UserDTO(String username, String email, String password, Map<String, Boolean> roles){
		this(username, email, password);
		this.roles = roles;
	}
	
	public UserDTO(User user){
		this(user.getUsername(), user.getEmail(), user.getPassword());
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	
	

}
