package org.pilirion.nakaza.components.panel.story;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.story.StoryDetail;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaStory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LastAddedStories extends Panel {
    public LastAddedStories(String id, List<NakazaStory> stories) {
        super(id);

        if(stories == null) {
            stories = new ArrayList<NakazaStory>();
        }

        add(new ListView<NakazaStory>("stories", stories) {

            @Override
            protected void populateItem(ListItem<NakazaStory> item) {
                NakazaStory story = item.getModelObject();

                PageParameters params = new PageParameters();
                params.add("id", story.getId());
                Link storyDetail = new BookmarkablePageLink<BasePage>("storyDetail", StoryDetail.class, params);
                Label publicDescription = new Label("publicDescription", Model.of(story.getName()));
                storyDetail.add(publicDescription);
                item.add(storyDetail);

                item.add(new ListView<NakazaLabel>("labels",story.getLabels()) {

                    @Override
                    protected void populateItem(ListItem<NakazaLabel> item) {
                        item.add(new Label("label",item.getModelObject().getName()));
                    }
                });
            }
        });
    }
}
