package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.story.AddStoryToCharacter;
import org.pilirion.nakaza.components.panel.story.CreateStoryPanel;
import org.pilirion.nakaza.components.panel.story.ListStoriesPanel;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.StoryService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StoryList extends BasePage {
    @SpringBean
    StoryService storyService;

    public StoryList() {
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getStoryButtons()));

        add(new ListStoriesPanel("storiesPanel", storyService.getAll()));

        add(new BookmarkablePageLink<BasePage>("login",Login.class));
    }
}
