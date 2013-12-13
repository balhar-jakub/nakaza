package org.pilirion.nakaza.components.panel.layout;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;

import java.util.List;

/**
 *
 */
public class LeftMenu extends Panel {
    public LeftMenu(String id, List<ButtonLike> buttons, String header) {
        super(id);

        add(new Label("header", Model.of(header)));

        add(new ListView<ButtonLike>("buttons", buttons) {

            @Override
            protected void populateItem(ListItem<ButtonLike> item) {
                ButtonLike buttonLike = item.getModelObject();

                PageParameters params = new PageParameters();
                if(buttonLike.getParams() != null) {
                    params = buttonLike.getParams();
                }
                Link buttonLink = new BookmarkablePageLink<BasePage>("button", buttonLike.getLinkClass(), params);
                Label buttonText = new Label("buttonText", Model.of(buttonLike.getButtonText()));
                buttonLink.add(buttonText);
                item.add(buttonLink);
            }
        });
    }
}
