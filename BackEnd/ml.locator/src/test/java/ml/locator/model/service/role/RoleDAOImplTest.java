/**
 * 
 */
package ml.locator.model.service.role;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ml.locator.model.entity.Role;

/**
 * May 30, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class RoleDAOImplTest {
	private static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private RoleDAO<Role, Integer> roleDAO;

	/**
	 * Test method for {@link ml.locator.model.service.role.RoleDAOImpl#findByName(java.lang.String)}.
	 */
	@Test
	public void testFindByName() {
		Role role = roleDAO.findByName(ROLE_USER);
		assertEquals(ROLE_USER, role.getName());
	}

	/**
	 * Test method for {@link ml.locator.model.service.JpaDAO#findAll()}.
	 */
	@Test
	public void testFindAll() {
		List<Role> roles = roleDAO.findAll();
		assertTrue(roles.size() > 0);
	}

	/**
	 * Test method for {@link ml.locator.model.service.JpaDAO#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		Integer id = 1;
		Role role = roleDAO.find(id);
		assertEquals(id, role.getRoleId());
		assertEquals(ROLE_USER, role.getName());
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
