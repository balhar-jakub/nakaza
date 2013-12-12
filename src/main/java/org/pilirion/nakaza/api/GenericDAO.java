package org.pilirion.nakaza.api;

import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * It is based on Generic DataAccessObject and therefore it provides basic methods needed to access and handle object.
 * Basically standard CRUD operations.
 */
public interface GenericDAO<T, ID extends Serializable> {
	 
    T findById(ID id);
 
    List<T> findAll();

    boolean saveOrUpdate(T entity);
 
    void delete(T entity);

    public List<T> findByCriteria(Criterion... criterion);
}
