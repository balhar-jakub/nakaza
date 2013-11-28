package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class CreateCharacterPanel extends Panel {
    public CreateCharacterPanel(String id) {
        super(id);

        add(new BookmarkablePageLink<BasePage>("createCharacter", CreateCharacter.class));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
