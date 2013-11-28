package org.pilirion.nakaza.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:55
 */
public interface GenericService<T> {
    T getById(Serializable id);

    boolean saveOrUpdate(T entity);

    List<T> getAll();

    void delete(T entity);

    List<T> findByExample(T entity);

    List<T> getUnique(T validatableEntity);

    List<T> getFirstChoices(String s, int auto_complete_choices);
}
