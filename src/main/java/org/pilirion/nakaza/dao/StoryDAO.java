package org.pilirion.nakaza.dao;

import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaStory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:51
 */
@Repository
public class StoryDAO extends GenericHibernateDAO<NakazaStory, Serializable> {
}
