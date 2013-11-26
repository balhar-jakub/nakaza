package org.pilirion.nakaza.components.panel.user;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.page.user.Registration;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class LoginBoxPanel extends Panel {
    public LoginBoxPanel(String id) {
        super(id);

        Link registration = new BookmarkablePageLink("registration", Registration.class);
        add(registration);

        Link login = new BookmarkablePageLink("login", Login.class);
        add(login);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(!NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
