package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.layout.NewsPanel;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@AuthorizeInstantiation({"User","Editor","Admin"})
public class SignOut extends BasePage {
    public SignOut()
    {
        add(new NakazaSignInPanel("signInPanel"));

        List<ButtonLike> upper = new ArrayList<ButtonLike>();
        upper.add(new ButtonLike("Domů", HomePage.class));
        upper.add(new ButtonLike("O hře", AboutGame.class));
        upper.add(new ButtonLike("O světě", AboutWorld.class));
        upper.add(new ButtonLike("Příběhy", StoryList.class));
        upper.add(new ButtonLike("Postavy", CharacterList.class));
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        add(new LeftMenus("leftMenus", upper, lower));

        add(new NewsPanel("newsPanel"));

        NakazaAuthenticatedWebSession.get().signOut();
        IAuthenticationStrategy strategy = getApplication().getSecuritySettings()
                .getAuthenticationStrategy();
        strategy.remove();

        final BookmarkablePageLink homePageLink =
                new BookmarkablePageLink<BasePage>("homePageLink", HomePage.class);
        add(homePageLink);
    }
}