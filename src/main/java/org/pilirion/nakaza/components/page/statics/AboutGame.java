package org.pilirion.nakaza.components.page.statics;

import org.apache.wicket.request.mapper.parameter.PageParameters;
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
public class AboutGame extends BasePage{
    public AboutGame(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getEmptyButtons()));

        add(new NewsPanel("newsPanel"));
    }
}
