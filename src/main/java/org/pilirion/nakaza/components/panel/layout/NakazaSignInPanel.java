package org.pilirion.nakaza.components.panel.layout;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.components.panel.user.LoggedBoxPanel;
import org.pilirion.nakaza.components.panel.user.LoginBoxPanel;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class NakazaSignInPanel extends Panel {
    public NakazaSignInPanel(String id) {
        super(id);
        add(new LoginBoxPanel("loginBox", false));
        add(new LoggedBoxPanel("loggedBox"));
    }
}
