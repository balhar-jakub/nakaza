package org.pilirion.nakaza.components.panel.user;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.authentication.IAuthenticationStrategy;
import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.page.user.Registration;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class LoginBoxPanel extends SignInPanel {
    private String username;
    private String password;


    public LoginBoxPanel(String id, boolean includeRememberMe) {
        super(id, includeRememberMe);
        add(new BookmarkablePageLink<BasePage>("registration", Registration.class));
    }

    /**
     * @see org.apache.wicket.Component#onBeforeRender()
     */
    @Override
    protected void onBeforeRender()
    {
        // logged in already?
        if (!isSignedIn())
        {
            IAuthenticationStrategy authenticationStrategy = getApplication().getSecuritySettings()
                    .getAuthenticationStrategy();
            // get username and password from persistence store
            String[] data = authenticationStrategy.load();

            if ((data != null) && (data.length > 1))
            {
                // try to sign in the user
                if (signIn(data[0], data[1]))
                {
                    username = data[0];
                    password = data[1];

                    // logon successful. Continue to the original destination
                    continueToOriginalDestination();
                    // Ups, no original destination. Go to the home page
                    throw new RestartResponseException(getSession().getPageFactory().newPage(
                            getApplication().getHomePage()));
                }
                else
                {
                    // the loaded credentials are wrong. erase them.
                    authenticationStrategy.remove();
                }
            }
        }

        // don't forget
        super.onBeforeRender();
    }

    protected boolean signIn(String username, String password){
        return (NakazaAuthenticatedWebSession.get()).signIn(username, password);
    }

    protected boolean isSignedIn(){
        return NakazaAuthenticatedWebSession.get().isSignedIn();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(!NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
