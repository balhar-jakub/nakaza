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
import org.pilirion.nakaza.entity.NakazaStory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ListStoriesShortPanel extends Panel {
    public ListStoriesShortPanel(String id, List<NakazaStory> stories) {
        super(id);

        if(stories == null){
            stories = new ArrayList<NakazaStory>();
        }
        add(new ListView<NakazaStory>("stories", stories) {
            @Override
            protected void populateItem(ListItem<NakazaStory> item) {
                NakazaStory story = item.getModelObject();

                PageParameters params = new PageParameters();
                params.set("id", story.getId());
                Link storyDetail = new BookmarkablePageLink<BasePage>("storyDetail", StoryDetail.class, params);
                Label storyName = new Label("storyName", Model.of(story.getName()));
                storyDetail.add(storyName);
                item.add(storyDetail);
            }
        });
    }
}
