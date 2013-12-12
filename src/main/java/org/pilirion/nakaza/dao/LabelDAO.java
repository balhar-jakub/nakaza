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
 * Ascendant of GenericHibernateDao, which handles Labels, therefore it implements getFirstChoices,
 * as Labels are used in autocompletables.
 */
@Repository
public class LabelDAO extends GenericHibernateDAO<NakazaLabel, Serializable> {
    @SuppressWarnings("unchecked")
    public List<NakazaLabel> getFirstChoices(String startsWith, int maxChoices) {
        Criteria query = sessionFactory.getCurrentSession().createCriteria(NakazaLabel.class);
        query.setMaxResults(maxChoices);
        query.add(Restrictions.ilike("name", "%" + startsWith + "%"));
        return query.list();
    }
}
