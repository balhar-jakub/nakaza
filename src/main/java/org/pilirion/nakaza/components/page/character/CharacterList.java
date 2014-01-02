package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.panel.character.CreateCharacterPanel;
import org.pilirion.nakaza.components.panel.character.ListCharacters;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CharacterList extends BasePage {
    @SpringBean
    UserService characterService;

    public CharacterList(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getCharacterButtons()));

        List<NakazaUser> charactersData = characterService.getCharacters();

        add(new ListCharacters("charactersPanel", charactersData));

        add(new ListShortCharacters("listShortChars", characterService.getFirstUsersWithCharacters()));

        add(new BookmarkablePageLink<BasePage>("login", Login.class));
    }
}
