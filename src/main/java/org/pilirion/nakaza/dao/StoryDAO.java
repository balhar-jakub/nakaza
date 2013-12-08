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

    public List<NakazaStory> getAllAproved(int groupId) {
        // Return only these stories that have at least one empty place of given group.
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("select story.id, story.description_private, story.description_public," +
                "story.points, story.state, story.name from nakaza_story as story join nakaza_participant as participant " +
                "on story.id = participant.story where story.state=true and participant.id_user is null and " +
                "participant.group_id = '" + groupId + "'").addEntity(NakazaStory.class);
        return query.list();
    }
}
