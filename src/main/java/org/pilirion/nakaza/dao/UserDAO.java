package org.pilirion.nakaza.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaUser;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Repository
public class UserDAO extends GenericHibernateDAO<NakazaUser, Serializable> {
    public NakazaUser authenticate(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from NakazaUser user " +
                " where user.email = :username and user.password = :password");
        query.setString("username", username);
        query.setString("password", password);
        return (NakazaUser) query.uniqueResult();
    }

    public List<NakazaUser> findByCriteria(Criterion criterion, int limit) {
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
        crit.setMaxResults(limit);
        crit.add(criterion);
        return crit.list();
    }
}
