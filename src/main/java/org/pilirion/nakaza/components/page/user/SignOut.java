package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 * Created with IntelliJ IDEA.
 * User: balhar
 * Date: 20.11.13
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public class SignOut extends BasePage {
    public SignOut(final PageParameters parameters)
    {
        NakazaAuthenticatedWebSession.get().signOut();
        IAuthenticationStrategy strategy = getApplication().getSecuritySettings()
                .getAuthenticationStrategy();
        strategy.remove();

        final BookmarkablePageLink homePageLink =
                new BookmarkablePageLink("homePageLink", HomePage.class);
        add(homePageLink);
    }
}