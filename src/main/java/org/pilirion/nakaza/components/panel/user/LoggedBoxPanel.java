package org.pilirion.nakaza.components.panel.user;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.pilirion.nakaza.Nakaza;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
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

        PageParameters params = new PageParameters();
        params.set("userId",logged.getId());
        Link characterDetail = new BookmarkablePageLink("characterDetail", CharacterDetail.class, params);
        add(characterDetail);

        Link logout = new BookmarkablePageLink("logout", SignOut.class);
        add(logout);

        Link edit = new BookmarkablePageLink("settings", Registration.class);
        add(edit);

        final Image loggedUserIcon = new Image("imageOfPerson",
                new PackageResourceReference(Nakaza.class, logged.getImage() != null ? logged.getImage() : ""));
        add(loggedUserIcon);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
