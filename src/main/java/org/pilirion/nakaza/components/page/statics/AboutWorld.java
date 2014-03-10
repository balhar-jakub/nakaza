package org.pilirion.nakaza.components.page.statics;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.layout.NewsPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AboutWorld extends BasePage {
    public AboutWorld(){
        init();
    }

    private void init(){
        add(new Image("survivor", new PackageResourceReference(BasePage.class, "img/survivor.jpg")));
        add(new Image("zombie", new PackageResourceReference(BasePage.class, "img/zombie.jpg")));
        add(new Image("army", new PackageResourceReference(BasePage.class, "img/army.gif")));

        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getAboutWorld()));

        add(new NewsPanel("newsPanel"));
    }
}
