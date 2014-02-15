package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.exception.TooManyPlayersInGroup;

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

    void changeCharacter(NakazaUser user) throws TooManyPlayersInGroup;

    boolean isZombieAvailable();

    boolean isSurvivorsAvailable();

    boolean isArmyAvailable();

    boolean removeStory(NakazaUser user, NakazaStory story);
}
