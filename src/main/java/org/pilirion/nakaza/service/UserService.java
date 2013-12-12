package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;

import java.util.List;

/**
 *
 */
public interface UserService extends GenericService<NakazaUser> {
    NakazaUser authenticate(String username, String password);

    List<NakazaUser> getCharacters();

    List<NakazaUser> getFirstUsersWithCharacters();

    NakazaUser getCharacterOfUser(int id);

    NakazaUser getLoggedUser();
}
