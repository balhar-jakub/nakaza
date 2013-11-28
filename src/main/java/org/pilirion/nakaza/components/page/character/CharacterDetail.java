package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.Nakaza;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.story.ListStoriesPanel;
import org.pilirion.nakaza.components.panel.story.ListStoriesShortPanel;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;

/**
 *
 */
public class CharacterDetail extends BasePage {
    @SpringBean
    UserService userService;

    public CharacterDetail(PageParameters params) {
        int NONE = -1;
        int id = params.get("id").toInt(NONE);

        NakazaUser user = userService.getById(id);
        if(id == NONE || user == null || user.getCharacter() == null) {
            throw new RestartResponseException(CharacterList.class);
        }
        NakazaCharacter character = user.getCharacter();

        add(new Image("avatarImage", new PackageResourceReference(Nakaza.class, user.getImage())));

        add(new Label("name", character.getName()));
        add(new Label("age", character.getAge()));
        add(new Label("descriptionPublic", character.getDescription()).setEscapeModelStrings(false));

        add(new ListStoriesPanel("listStories", user.getStories()));
        add(new ListStoriesShortPanel("shortStories", user.getStories()));
    }
}
