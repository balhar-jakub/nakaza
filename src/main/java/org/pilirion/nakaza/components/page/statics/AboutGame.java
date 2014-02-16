package org.pilirion.nakaza.components.page.statics;

import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.layout.NewsPanel;

/**
 *
 */
public class AboutGame extends BasePage{
    public AboutGame(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getAboutGame()));

        add(new NewsPanel("newsPanel"));
    }
}
