package org.pilirion.nakaza;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 4.9.13
 * Time: 18:02
 */
@Component(value = "wicketApplication")
public class Nakaza extends AuthenticatedWebApplication {
    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return null;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return null;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return null;
    }
}
