package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.pilirion.nakaza.Nakaza;
import org.pilirion.nakaza.components.panel.story.ListStoriesPanel;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class CharacterDetailPanel extends Panel {
    @Autowired
    UserService userService;

    public CharacterDetailPanel(String id, NakazaUser user) {
        super(id);

        NakazaCharacter character = user.getCharacter();
        add(new Image("avatarImage", new PackageResourceReference(Nakaza.class, user.getImage())));

        add(new Label("group", character.getGroupText()));
        add(new Label("name", character.getName()));
        add(new Label("age", character.getAge()));
        add(new Label("descriptionPublic", character.getDescription()).setEscapeModelStrings(false));

        add(new ListStoriesPanel("listStories", user.getStories()));
    }
}
