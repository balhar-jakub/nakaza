package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.character.CreateOrUpdateLogged;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.service.UserService;

import java.util.List;

/**
 *
 */
public class CreateCharacter extends BasePage {
    @SpringBean
    UserService userService;

    public CreateCharacter(){
        init();
    }

    private void init(){
        NakazaUser user = ((NakazaAuthenticatedWebSession)NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(user == null) {
            user = NakazaUser.getEmptyUser();
        } else {
            if(user.getCharacter() == null) {
                user.setCharacter(new NakazaCharacter());
            }
        }

        add(new CreateOrUpdateLogged("createOrUpdateLogged", user));

        List<NakazaUser> nakazaUsers = userService.getFirstUsersWithCharacters();

        add(new ListShortCharacters("shortCharacters", nakazaUsers));
    }
}
