package org.pilirion.nakaza.api;

import org.apache.wicket.model.IModel;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

/**
 *
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
        return (T) genericDAO.findById(id);
    }

    public void setObject(T object)
    {
        throw new UnsupportedOperationException(getClass() +
                " does not support #setObject(T entity)");
    }
}
