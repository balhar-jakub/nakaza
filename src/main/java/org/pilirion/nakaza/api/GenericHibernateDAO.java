package org.pilirion.nakaza.api;

import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Generic hibernate Data access object, it provides basic methods to anything implementing the DAO. Basically
 * it provides standard CRUD operations.
 *
 * @param <T> Entity to be handled by specific Ancestor.
 * @param <ID> Id of the entity.
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable>
		implements GenericDAO<T, ID> {

	@Autowired
	protected SessionFactory sessionFactory;

	public GenericHibernateDAO() {
	}

    @SuppressWarnings("unchecked")
	protected Class<T> getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}

    /**
     * It returns unique entity found by Id (Unique identifier). It expects existence of entity with given Id. .
     *
     * @param id Unique identifier of entity.
     * @return Specific entity
     */
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T entity = (T) sessionFactory.getCurrentSession().load(getPersistentClass(), id);
		return entity;
	}

    /**
     * It returns all entities of given type in the database.
     *
     * @return List of all entities. Carefull, list may be returned as PersistentBag.
     */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return findByCriteria();
	}

    /**
     * It deletes given entity.
     *
     * @param entity Entity to be deleted.
     */
    public void delete(T entity) {
        Transaction tx = sessionFactory.getCurrentSession().beginTransaction();
		sessionFactory.getCurrentSession().delete(entity);
        sessionFactory.getCurrentSession().flush();
        tx.commit();
	}

    /**
     * It saves or updates given entity, if the entity already exists.
     *
     * @param entity entity to be changed.
     * @return true if the operation was successful.
     */
    public boolean saveOrUpdate(T entity) {
        try{
            sessionFactory.getCurrentSession().saveOrUpdate(entity);
            sessionFactory.getCurrentSession().flush();
            return true;
        } catch (HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

	/**
	 * Use this inside subclasses as a convenience method.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
}
