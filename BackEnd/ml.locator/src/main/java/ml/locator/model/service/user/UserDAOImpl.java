package ml.locator.model.service.user;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ml.locator.model.entity.User;
import ml.locator.model.service.JpaDAO;

@Stateless
public class UserDAOImpl extends JpaDAO<User,String>  implements UserDAO<User, String> {
	
	public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByName(username);
        if (null == user) {
            throw new UsernameNotFoundException("The user with name " + username + " was not found");
        }

        return user;
    }

    @Override
    public User findByName(String name)
    {
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<User> criteriaQuery = builder.createQuery(this.entityClass);

        Root<User> root = criteriaQuery.from(this.entityClass);
        javax.persistence.criteria.Path<String> namePath = root.get("username");
        criteriaQuery.where(builder.equal(namePath, name));

        TypedQuery<User> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        List<User> users = typedQuery.getResultList();

        if (users.isEmpty()) {
            return null;
        }

        return users.iterator().next();
    	
    }
    
	@Override
	public User find(String id) {
		return findByName(id);
	}
    
    

}
