package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
@AuthorizeInstantiation({"User","Editor","Admin"})
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