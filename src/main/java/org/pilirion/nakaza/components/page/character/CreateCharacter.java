package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.character.CreateOrUpdateLogged;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class CreateCharacter extends BasePage {
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
    }
}
