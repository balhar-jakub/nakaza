package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.entity.NakazaCharacter;

import java.util.List;

/**
 *
 */
public class ListCharacters extends Panel {
    public ListCharacters(String id, List<NakazaCharacter> characters) {
        super(id);

        add(new ListView<NakazaCharacter>("characters", characters) {
            @Override
            protected void populateItem(ListItem<NakazaCharacter> item) {
                NakazaCharacter character = item.getModelObject();

                Link characterDetail = new BookmarkablePageLink<BasePage>("characterDetail", CharacterDetail.class);
                characterDetail.add(new Label("name", character.getName()));
                item.add(characterDetail);
                item.add(new Label("age", character.getAge()));
                item.add(new Label("group", character.getGroup()));
                item.add(new Label("description", character.getDescription()).setEscapeModelStrings(false));
            }
        });
    }
}
