package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.authroles.authentication.panel.SignInPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;

/**
 *
 */
public class Login extends BasePage {
    public Login(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){
        add(new SignInPanel("signIn", true));
    }
}
