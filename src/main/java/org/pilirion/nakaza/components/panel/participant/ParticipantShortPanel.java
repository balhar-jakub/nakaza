package org.pilirion.nakaza.components.panel.participant;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.entity.NakazaParticipant;

import java.util.List;

/**
 *
 */
public class ParticipantShortPanel extends Panel {
    public ParticipantShortPanel(String id, List<NakazaParticipant> participants) {
        super(id);

        add(new ListView<NakazaParticipant>("participants", participants) {
            @Override
            protected void populateItem(ListItem<NakazaParticipant> item) {
                item.add(new Label("participant", item.getModelObject().getName()));
            }
        });
    }
}
