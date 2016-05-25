package ml.locator.model.service;

import java.util.List;

public interface DAO<T,I> {
	 
	List<T> findAll();
    T find(I id);
    T save(T newsEntry);
    void delete(I id);
}
