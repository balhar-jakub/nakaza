package org.pilirion.nakaza.components.panel.story;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.story.AddStory;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

/**
 *
 */
public class AddStoryToCharacter extends Panel {
    public AddStoryToCharacter(String id) {
        super(id);

        add(new BookmarkablePageLink<BasePage>("addStoryToCharacter", AddStory.class));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
