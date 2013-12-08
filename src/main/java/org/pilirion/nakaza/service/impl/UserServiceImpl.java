package org.pilirion.nakaza.service.impl;

import org.hibernate.criterion.Restrictions;
import org.pilirion.nakaza.dao.UserDAO;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:53
 */
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public NakazaUser getById(Serializable id) {
        return userDAO.findById(id, false);
    }

    @Override
    public boolean saveOrUpdate(NakazaUser user) {
        return userDAO.saveOrUpdate(user);
    }

    @Override
    public List<NakazaUser> getAll() {
        return userDAO.findAll();
    }

    @Override
    public void delete(NakazaUser user) {
        userDAO.makeTransient(user);
    }

    @Override
    public List<NakazaUser> findByExample(NakazaUser user) {
        return userDAO.findByExample(user, new String[]{});
    }

    @Override
    public List<NakazaUser> getUnique(NakazaUser validatableEntity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<NakazaUser> getFirstChoices(String s, int auto_complete_choices) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public NakazaUser authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    @Override
    public List<NakazaUser> getCharacters() {
        List<NakazaUser> users = userDAO.findByCriteria(Restrictions.isNotNull("character.name"));
        return users;
    }

    @Override
    public List<NakazaUser> getFirstUsersWithCharacters() {
        return userDAO.findByCriteria(Restrictions.isNotNull("character.name"), 5);
    }
}
