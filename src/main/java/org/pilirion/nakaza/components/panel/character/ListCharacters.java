package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;

import java.util.List;

/**
 *
 */
public class ListCharacters extends Panel {
    public ListCharacters(String id, List<NakazaUser> characters) {
        super(id);

        add(new ListView<NakazaUser>("characters", characters) {
            @Override
            protected void populateItem(ListItem<NakazaUser> item) {
                NakazaCharacter character = item.getModelObject().getCharacter();

                PageParameters params = new PageParameters();
                params.add("id",item.getModelObject().getId());
                Link characterDetail = new BookmarkablePageLink<BasePage>("characterDetail", CharacterDetail.class, params);
                characterDetail.add(new Label("name", character.getName()));
                item.add(characterDetail);
                item.add(new Label("age", character.getAge()));
                item.add(new Label("group", character.getGroup()));
                item.add(new Label("description", character.getDescription()).setEscapeModelStrings(false));
            }
        });
    }
}
