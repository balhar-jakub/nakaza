package org.pilirion.nakaza.dao;

import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:33
 */
@Repository
public class LabelDAO extends GenericHibernateDAO<NakazaLabel, Serializable> {
}
