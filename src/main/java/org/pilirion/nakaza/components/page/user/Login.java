package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Login extends BasePage {
    public Login(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){
        add(new NakazaSignInPanel("signInPanel"));

        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        add(new LeftMenus("leftMenus", Menu.getMainButtons(), lower));

        add(new SignInPanel("signIn", true));
    }
}
