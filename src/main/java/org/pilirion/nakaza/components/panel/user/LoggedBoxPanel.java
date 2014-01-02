package org.pilirion.nakaza.components.panel.user;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.user.Registration;
import org.pilirion.nakaza.components.page.user.SignOut;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class LoggedBoxPanel extends Panel {
    public LoggedBoxPanel(String id) {
        super(id);

        NakazaUser logged = ((NakazaAuthenticatedWebSession)NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(logged == null) {
            logged = NakazaUser.getEmptyUser();
        }

        Link characterDetail;
        if(logged.getCharacter() == null) {
            characterDetail = new BookmarkablePageLink<BasePage>("characterDetail", CreateCharacter.class);
        } else {
            PageParameters params = new PageParameters();
            params.set("id",logged.getId());
            characterDetail = new BookmarkablePageLink<BasePage>("characterDetail", CharacterDetail.class, params);
        }
        add(characterDetail);

        Link logout = new BookmarkablePageLink<BasePage>("logout", SignOut.class);
        add(logout);

        Link edit = new BookmarkablePageLink<BasePage>("settings", Registration.class);
        add(edit);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
