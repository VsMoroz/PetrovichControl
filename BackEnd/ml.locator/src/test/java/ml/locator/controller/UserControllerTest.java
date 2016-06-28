/**
 * 
 */
package ml.locator.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ml.locator.model.UserDTO;

/**
 * May 31, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UserControllerTest {
	private static final String LOGIN = "user";
	private static final String PASSWORD = "pass";

	@Autowired
	private UserController userController;

	@BeforeClass
	public static void setup(){
		Authentication auth = new UsernamePasswordAuthenticationToken(LOGIN, PASSWORD);
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(auth);
	}
	
	/**
	 * Test method for {@link ml.locator.controller.UserController#getPrinsipal()}.
	 */
	@Test
	public void testGetPrinsipal() throws Exception {
		UserDTO userDTO = userController.getPrinsipal();
		assertNotNull(userDTO);
		assertEquals(LOGIN, userDTO.getUsername());
	}

	/**
	 * Test method for {@link ml.locator.controller.UserController#register(ml.locator.model.UserDTO, java.util.List)}.
	 */
	@Test
	public void testRegister() throws Exception {
	
		UserDTO registeringUser = new UserDTO();
		String username = "User_" + new Random().nextInt(100);
		registeringUser.setUsername(username);
		registeringUser.setPassword("junit");
		registeringUser.setEmail("11yaan@ukr.net");
		
		String[] userRoles = {"ROLE_USER"};
		
		Response response = userController.register(username, "11yaan@ukr.net", "junit", userRoles);
		assertTrue(response.getStatus() == 200);
		
	}
	
	
	

}
