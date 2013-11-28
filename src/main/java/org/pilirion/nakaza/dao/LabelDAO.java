package org.pilirion.nakaza.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Repository
public class LabelDAO extends GenericHibernateDAO<NakazaLabel, Serializable> {
    public List<NakazaLabel> getFirstChoices(String startsWith, int maxChoices) {
        Session session = sessionFactory.getCurrentSession();
        Criteria query = session.createCriteria(NakazaLabel.class);
        query.setMaxResults(maxChoices);
        query.add(Restrictions.ilike("name", "%" + startsWith + "%"));
        return query.list();
    }
}
