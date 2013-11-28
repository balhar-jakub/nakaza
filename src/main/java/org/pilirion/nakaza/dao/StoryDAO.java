package org.pilirion.nakaza.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaStory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:51
 */
@Repository
public class StoryDAO extends GenericHibernateDAO<NakazaStory, Serializable> {
    public List<NakazaStory> getLastAdded(int maxStories) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from NakazaStory order by id desc");
        query.setMaxResults(maxStories);
        return query.list();
    }
}
