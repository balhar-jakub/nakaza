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
        Query query = sessionFactory.getCurrentSession().createSQLQuery("select story.id, story.description_private, story.description_public," +
                "story.points, story.state, story.name from nakaza_story as story join nakaza_participant as participant " +
                "on story.id = participant.story where story.state=true and participant.id_user is null and " +
                "participant.group_id = '" + groupId + "'").addEntity(NakazaStory.class);
        return query.list();
    }
}
