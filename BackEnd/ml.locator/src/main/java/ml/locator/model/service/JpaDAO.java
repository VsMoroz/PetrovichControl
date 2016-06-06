package ml.locator.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


public class JpaDAO<T,I> implements DAO<T, I> {
		
	@PersistenceContext
	private EntityManager entityManager;

    protected Class<T> entityClass;

    public JpaDAO() {}

    public JpaDAO(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager(){
        return this.entityManager;
    }
    
    public void setEntityManager(final EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findAll(){
        final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<T> criteriaQuery = builder.createQuery(this.entityClass);

        criteriaQuery.from(this.entityClass);

        TypedQuery<T> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        return typedQuery.getResultList();   	
    }

    @Override
    public T find(I id){
        return this.getEntityManager().find(this.entityClass, id);
    }

    @Override
    public T save(T entity){
        return this.getEntityManager().merge(entity);
    }

    @Override
    public void delete(I id){
        if (id == null) {
            return;
        }

        T entity = this.find(id);
        if (entity == null) {
            return;
        }

        this.getEntityManager().remove(entity);
    }
}
