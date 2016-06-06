/**
 * 
 */
package ml.locator.model.service.role;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import ml.locator.model.entity.Role;
import ml.locator.model.service.JpaDAO;

/**
 * May 30, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
@Stateless
public class RoleDAOImpl extends JpaDAO<Role, Integer> implements RoleDAO<Role, Integer> {

	public RoleDAOImpl(){
		super(Role.class);
	}
	
	@Override
	public Role findByName(String name) {
		
		CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
		
		Root<Role> root = criteriaQuery.from(this.entityClass);
		Path<String> namePath = root.get("name"); 
		criteriaQuery.where(criteriaBuilder.equal(namePath, name));
		
		TypedQuery<Role> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		List<Role> roles = typedQuery.getResultList();
		
		if (roles.isEmpty()) return null;
		
		return roles.iterator().next();
	}
}
