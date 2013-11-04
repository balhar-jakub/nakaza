package org.pilirion.nakaza.dao;

import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaUser;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:52
 */
@Repository
public class UserDAO extends GenericHibernateDAO<NakazaUser, Serializable> {
}
