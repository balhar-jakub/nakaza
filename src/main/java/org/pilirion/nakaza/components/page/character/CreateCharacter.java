package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.character.CreateOrUpdateLogged;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;

import java.util.List;

/**
 *
 */
@AuthorizeInstantiation({"Admin", "Editor", "User"})
public class CreateCharacter extends BasePage {
    @SpringBean
    UserService userService;

    public CreateCharacter(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getCharacterButtons()));

        add(new CreateOrUpdateLogged("createOrUpdateLogged"));

        List<NakazaUser> nakazaUsers = userService.getMostRecentWithCharacters();
        add(new ListShortCharacters("shortCharacters", nakazaUsers));
    }
}
