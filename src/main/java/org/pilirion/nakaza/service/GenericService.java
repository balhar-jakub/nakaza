package org.pilirion.nakaza.service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:55
 */
public interface GenericService<T, Serializable> {
    T getById(Serializable id);

    boolean saveOrUpdate(T entity);

    List<T> getAll();

    void delete(T entity);

    List<T> findByExample(T entity);
}
