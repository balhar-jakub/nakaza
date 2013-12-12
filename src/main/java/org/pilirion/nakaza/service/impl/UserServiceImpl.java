package org.pilirion.nakaza.service.impl;

import org.apache.wicket.RestartResponseException;
import org.hibernate.criterion.Restrictions;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.dao.UserDAO;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public NakazaUser getById(Serializable id) {
        return userDAO.findById(id);
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
        userDAO.delete(user);
    }

    @Override
    public NakazaUser authenticate(String username, String password) {
        return userDAO.authenticate(username, password);
    }

    @Override
    public List<NakazaUser> getCharacters() {
        return userDAO.findByCriteria(Restrictions.isNotNull("character.name"));
    }

    @Override
    public List<NakazaUser> getFirstUsersWithCharacters() {
        return userDAO.getFirstWithCharacters(5);
    }

    @Override
    public NakazaUser getCharacterOfUser(int id) {
        int NONE = -1;
        NakazaUser user = getById(id);

        if(id == NONE || user == null || user.getCharacter() == null) {
            throw new RestartResponseException(CharacterList.class);
        }
        return user;
    }

    @Override
    public NakazaUser getLoggedUser() {
        NakazaUser user = ((NakazaAuthenticatedWebSession) NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(user == null) {
            return null;
        }
        return getById(user.getId());
    }

    @Override
    public List<NakazaUser> getUnique(NakazaUser validatableEntity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<NakazaUser> getFirstChoices(String s, int auto_complete_choices) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
