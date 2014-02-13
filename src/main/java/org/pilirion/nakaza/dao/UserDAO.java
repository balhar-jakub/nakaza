package org.pilirion.nakaza.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Implementation of FenericHibernateDAO for NakazaUser
 */
@Repository
public class UserDAO extends GenericHibernateDAO<NakazaUser, Serializable> {
    /**
     * It is used for purpose of authentication.
     *
     * @param username Username
     * @param password Password, already transformed.
     * @return If exists, user with given username and password or null, if it does not exists.
     */
    public NakazaUser authenticate(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from NakazaUser user " +
                " where user.email = :username and user.password = :password");
        query.setString("username", username);
        query.setString("password", password);
        return (NakazaUser) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<NakazaUser> getFirstWithCharacters(int limit) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
        crit.setMaxResults(limit);
        crit.add(Restrictions.isNotNull("character.name"));
        return crit.list();
    }

    public int getZombies() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NakazaUser.class);
        criteria.add(Restrictions.eq("character.group","0"));
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    public int getSurvivors() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NakazaUser.class);
        criteria.add(Restrictions.eq("character.group","1"));
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }

    public int getArmy() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NakazaUser.class);
        criteria.add(Restrictions.eq("character.group","2"));
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }
}
