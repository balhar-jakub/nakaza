package org.pilirion.nakaza.components.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.entity.NakazaLabel;

import java.util.List;

/**
 *
 */
public class LabelsPanel extends Panel {
    public LabelsPanel(String id, List<NakazaLabel> labels) {
        super(id);

        add(new ListView<NakazaLabel>("labels", labels) {
            @Override
            protected void populateItem(ListItem<NakazaLabel> item) {
                item.add(new Label("label",item.getModelObject().getName()));
            }
        });
    }
}
