package org.pilirion.nakaza.api;

import org.apache.wicket.model.IModel;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 28.4.13
 * Time: 18:16
 */
public class EntityModel<T extends Identifiable< ? >> implements IModel<T>
{
    private final Class clazz;
    private GenericDAO genericDAO;
    private Serializable id;

    private T entity;

    public EntityModel(T entity, GenericDAO genericDAO)
    {
        this.genericDAO = genericDAO;
        clazz = entity.getClass();
        id = entity.getId();
        this.entity = entity;
    }

    public EntityModel(Class<? extends T> clazz, Serializable id, GenericDAO genericDAO)
    {
        this.genericDAO = genericDAO;
        this.clazz = clazz;
        this.id = id;
    }

    public T getObject()
    {
        if (entity == null)
        {
            if (id != null)
            {
                entity = load(clazz, id);
                if (entity == null)
                {
                    throw new EntityNotFoundException();
                }
            }
        }
        return entity;
    }

    public void detach()
    {
        if (entity != null)
        {
            if (entity.getId() != null)
            {
                id = entity.getId();
                entity = null;
            }
        }
    }

    protected T load(Class clazz, Serializable id){
        return (T) genericDAO.findById(id, false);
    }

    public void setObject(T object)
    {
        throw new UnsupportedOperationException(getClass() +
                " does not support #setObject(T entity)");
    }
}
