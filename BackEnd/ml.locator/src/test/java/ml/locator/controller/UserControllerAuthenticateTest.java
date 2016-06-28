/**
 * 
 */
package ml.locator.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ml.locator.model.TokenDTO;
import ml.locator.model.entity.User;
import ml.locator.model.service.user.UserDAO;
import ml.locator.security.TokenUtils;

/**
 * Jun 28, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:WebContent/WEB-INF/applicationContextForTests.xml")
public class UserControllerAuthenticateTest {
	
	@Autowired
	private UserController userController;

	/**
	 * Test method for {@link ml.locator.controller.UserController#authenticate(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAuthenticate() throws Exception{
		
		TokenDTO token = userController.authenticate("user", "pass");
		assertNotNull(token);
		String[] parts = token.getToken().split(":");
		assertEquals("user", parts[0]);
		long tokenMillis = Long.parseLong(parts[1]);
		long millis = System.currentTimeMillis();
		assertTrue(tokenMillis > millis);
	
		
	}

}
