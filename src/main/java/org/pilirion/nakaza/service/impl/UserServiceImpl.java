package org.pilirion.nakaza.service.impl;

import org.pilirion.nakaza.dao.UserDAO;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public NakazaUser getById(Integer id) {
        return userDAO.findById(id, false);
    }

    @Override
    public void saveOrUpdate(NakazaUser user) {
        userDAO.saveOrUpdate(user);
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
}
