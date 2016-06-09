/**
 * 
 */
package ml.locator.model.service.user;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ml.locator.model.entity.User;

/**
 * May 30, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class UserDAOImplTest {
	private static final String USERNAME = "user";
	
	private static Context  ctx;
    private static EJBContainer ejbContainer;
	
	@BeforeClass
	public static void init() throws Exception{
		 ejbContainer = EJBContainer.createEJBContainer();
	     System.out.println("Opening the container" );
	     ctx = ejbContainer.getContext();
	}
	
	@AfterClass
    public static void tearDown() {
        ejbContainer.close();
        System.out.println("Closing the container" );
    }

	/**
	 * Test method for {@link ml.locator.model.service.user.UserDAOImpl#loadUserByUsername(java.lang.String)}.
	 */
	@Test(expected = UsernameNotFoundException.class)
	public void testLoadUserByUsername() throws Exception{
		@SuppressWarnings("unchecked")
		UserDAO<User, String> userDAO = (UserDAO<User, String>)ctx.lookup("java:global/process/UserDAOImpl!ml.locator.model.service.user.UserDAO");
		userDAO.loadUserByUsername("UnexistingUser");
	}

	/**
	 * Test method for {@link ml.locator.model.service.user.UserDAOImpl#findByName(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testFindByName() throws Exception {
		UserDAO<User, String> userDAO = (UserDAO<User, String>)ctx.lookup("java:global/process/UserDAOImpl!ml.locator.model.service.user.UserDAO");
		User user = userDAO.findByName(USERNAME);
		
		assertNotNull(user);
		assertEquals(USERNAME, user.getUsername());
	}
	
	/**
	 * Test method for {@link ml.locator.model.service.JpaDAO#findAll()}.
	 */
	@Ignore
	@Test
	public void testFindAll() throws Exception {
		UserDAO<User, String> userDAO = (UserDAO<User, String>)ctx.lookup("java:global/process/UserDAOImpl!ml.locator.model.service.user.UserDAO");
		List<User> users = userDAO.findAll();
		assertTrue(users.size() > 0);
	}

	/**
	 * Test method for {@link ml.locator.model.service.JpaDAO#find(java.lang.Object)}.
	 */
	@Ignore
	@Test(expected=UnsupportedOperationException.class)
	public void testFind() throws Exception {
		UserDAO<User, String> userDAO = (UserDAO<User, String>)ctx.lookup("java:global/process/UserDAOImpl!ml.locator.model.service.user.UserDAO");
		userDAO.find(USERNAME);
	}

	/**
	 * Test method for {@link ml.locator.model.service.JpaDAO#save(java.lang.Object)}.
	 */
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ml.locator.model.service.JpaDAO#delete(java.lang.Object)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

}
