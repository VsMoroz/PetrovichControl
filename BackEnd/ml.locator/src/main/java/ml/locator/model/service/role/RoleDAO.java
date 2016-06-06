/**
 * 
 */
package ml.locator.model.service.role;

import javax.ejb.Local;

import ml.locator.model.service.DAO;

/**
 * May 30, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@Local
public interface RoleDAO<T, I> extends DAO<T, I>{
	
	T findByName(String name);

}
