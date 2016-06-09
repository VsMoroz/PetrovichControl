/**
 * 
 */
package ml.locator.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ml.locator.utils.registration.PreRegisterCache;
import ml.locator.utils.registration.RegisterLink;
import ml.locator.utils.registration.RegisterLinkGenerator;

/**
 * Jun 7, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreRegisterCacheTest {
	private static final String HOST = "http://localhost:8080/process/api/user/confirm?";
	private static final String EMAIL = "test@test.tst";
	private static final String LOGIN = "JUnitTester";
	private static RegisterLink link;
	
	@Autowired
	private PreRegisterCache preRegisterCache;
	
	@BeforeClass
	public static void init() throws Exception{
		RegisterLinkGenerator registerLinkGenerator = new RegisterLinkGenerator(HOST, EMAIL);
		link = new RegisterLink(HOST, EMAIL, registerLinkGenerator.generateKey());
	}

	/**
	 * Test method for {@link ml.locator.utils.registration.PreRegisterCache#put(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void T001_testPut() {
		preRegisterCache.put(LOGIN, link);
		assertNotNull(preRegisterCache);
	}

	/**
	 * Test method for {@link ml.locator.utils.registration.PreRegisterCache#getLink(java.lang.String)}.
	 */
	@Test
	public void T002_testGetLink() {
		RegisterLink retrivedLink = preRegisterCache.getLink(LOGIN);
		assertEquals(retrivedLink, link);
	}

	/**
	 * Test method for {@link ml.locator.utils.registration.PreRegisterCache#getLogin(java.lang.String)}.
	 */
	@Test
	public void T003_testGetLogin() {
		String login = preRegisterCache.getLogin(link);
		assertEquals(login, LOGIN);
	}
	
	/**
	 * Test method for {@link ml.locator.utils.registration.PreRegisterCache#getLogin(java.lang.String)}.
	 */
	@Test
	public void T004_testGetLoginByKey() {
		String key = link.getKey();
		String login = preRegisterCache.getLogin(key);
		assertEquals(login, LOGIN);
	}
	
	/**
	 * Test method for {@link ml.locator.utils.registration.PreRegisterCache#isEmpty()}.
	 */
	@Test
	public void T005_testIsEmpty() {
		preRegisterCache.clear();
		assertTrue(preRegisterCache.isEmpty());
	}
	
	

}
