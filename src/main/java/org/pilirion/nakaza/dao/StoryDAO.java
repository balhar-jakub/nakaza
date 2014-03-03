package org.pilirion.nakaza.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaStory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Implementation of GenericHibernateDAO for NakazaStory.
 */
@Repository
public class StoryDAO extends GenericHibernateDAO<NakazaStory, Serializable> {
    /**
     * It returns give amount of stories which were added last.
     *
     * @param maxStories amount of stories to be retrieved at maximum.
     * @return List of found stories. If none is found it returns empty list.
     */
    @SuppressWarnings("unchecked")
    public List<NakazaStory> getLastAdded(int maxStories) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from NakazaStory order by id desc");
        query.setMaxResults(maxStories);
        return query.list();
    }

    /**
     * It returns all stories which are approved and have free place for the participant of given group.
     *
     * @param groupId Id of the group to be added.
     * @return List of available stories, if none is available it returns empty list.
     */
    @SuppressWarnings("unchecked")
    public List<NakazaStory> getAllApproved(int groupId) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from nakaza_story " +
                "where state = true and id in " +
                "(select story from nakaza_participant where group_id = '"+groupId+"' and id_user is null group by story)").
                addEntity(NakazaStory.class);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<NakazaStory> getApproved(){
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from nakaza_story " +
                "where state = true").
                addEntity(NakazaStory.class);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<NakazaStory> getLastAddedApproved(int max_stories) {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select * from nakaza_story " +
                "where state = true order by id desc limit :max_stories").
                addEntity(NakazaStory.class);
        query.setParameter("max_stories", max_stories);
        return query.list();
    }
}
