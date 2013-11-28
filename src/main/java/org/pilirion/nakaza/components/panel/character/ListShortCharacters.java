package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.pilirion.nakaza.Nakaza;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.components.panel.story.ListStoriesShortPanel;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;

import java.util.List;

/**
 *
 */
public class ListShortCharacters extends Panel {
    public ListShortCharacters(String id, List<NakazaUser> users) {
        super(id);

        add(new ListView<NakazaUser>("characters", users) {
            @Override
            protected void populateItem(ListItem<NakazaUser> item) {
                NakazaUser user = item.getModelObject();
                if(user.getCharacter() == null) {
                    return;
                }
                NakazaCharacter character = user.getCharacter();

                PageParameters params = new PageParameters();
                params.add("id", user.getId());
                Link characterDetail = new BookmarkablePageLink<BasePage>("characterDetail", CharacterDetail.class, params);
                characterDetail.add(new Image("characterImage", new PackageResourceReference(Nakaza.class, user.getImage())));
                item.add(characterDetail);

                item.add(new Label("description", character.getDescription()).setEscapeModelStrings(false));
                item.add(new ListStoriesShortPanel("shortStories", user.getStories()));
            }
        });
    }
}
