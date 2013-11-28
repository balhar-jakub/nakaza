package org.pilirion.nakaza.components.page;

import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.user.LoggedBoxPanel;
import org.pilirion.nakaza.components.panel.user.LoginBoxPanel;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class BasePage extends WebPage {
    public BasePage(){
        if(!NakazaAuthenticatedWebSession.get().isSignedIn()){
            IAuthenticationStrategy strategy = getApplication().getSecuritySettings()
                    .getAuthenticationStrategy();
            String[] data = strategy.load();
            if(data != null && data.length > 1){
                NakazaAuthenticatedWebSession.get().signIn(data[0], data[1]);
            }
        }

        Link aboutPage = new BookmarkablePageLink<BasePage>("homePage", HomePage.class);
        add(aboutPage);

        Link aboutGame = new BookmarkablePageLink<BasePage>("aboutGame", AboutGame.class);
        add(aboutGame);

        Link aboutWorld = new BookmarkablePageLink<BasePage>("aboutWorld", AboutWorld.class);
        add(aboutWorld);

        Link characters = new BookmarkablePageLink<BasePage>("characters", CharacterList.class);
        add(characters);

        Link stories = new BookmarkablePageLink<BasePage>("stories", StoryList.class);
        add(stories);

        add(new LoginBoxPanel("loginBox"));
        add(new LoggedBoxPanel("loggedBox"));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings()
                .getJQueryReference()));

        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(BasePage.class,"js/jquery-ui.min.js")));

        super.renderHead(response);
    }

    @Override
    protected void setHeaders(WebResponse response) {
        super.setHeaders(response);
        //Protection against ClickJacking, prevents the page from being rendered in an iframe element
        response.setHeader("X-Frame-Options","deny");
    }
}
