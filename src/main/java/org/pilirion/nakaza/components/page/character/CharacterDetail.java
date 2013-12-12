package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.character.CharacterDetailPanel;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.story.ListStoriesShortPanel;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;

/**
 * Detail of the character.
 */
public class CharacterDetail extends BasePage {
    @SpringBean
    UserService userService;

    public CharacterDetail(PageParameters params) {
        int NONE = -1;
        int id = params.get("id").toInt(NONE);

        add(new NakazaSignInPanel("signInPanel"));
        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getCharacterButtons()));

        NakazaUser user = userService.getCharacterOfUser(id);

        add(new CharacterDetailPanel("character-detail", user));
        add(new ListStoriesShortPanel("shortStories", user.getStories()));

    }
}
