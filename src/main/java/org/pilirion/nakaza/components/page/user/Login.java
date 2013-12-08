package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
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

        List<ButtonLike> upper = new ArrayList<ButtonLike>();
        upper.add(new ButtonLike("Domů", HomePage.class));
        upper.add(new ButtonLike("O hře", AboutGame.class));
        upper.add(new ButtonLike("O světě", AboutWorld.class));
        upper.add(new ButtonLike("Příběhy", StoryList.class));
        upper.add(new ButtonLike("Postavy", CharacterList.class));
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        add(new LeftMenus("leftMenus", upper, lower));

        add(new SignInPanel("signIn", true));
    }
}
