package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:57
 */
public interface UserService extends GenericService<NakazaUser> {
    NakazaUser authenticate(String username, String password);

    List<NakazaCharacter> getCharacters();

    List<NakazaUser> getFirstUsersWithCharacters();
}
