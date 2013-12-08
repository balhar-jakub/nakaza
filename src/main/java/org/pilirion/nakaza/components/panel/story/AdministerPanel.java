package org.pilirion.nakaza.components.panel.story;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.story.AdministerStories;
import org.pilirion.nakaza.components.page.story.StoryDetail;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.service.StoryService;

import java.util.List;

/**
 *
 */
public class AdministerPanel extends Panel {
    @SpringBean
    StoryService storyService;

    public AdministerPanel(String id, List<NakazaStory> stories) {
        super(id);

        setOutputMarkupId(true);

        Form form = new Form("storyForm");

        form.add(new ListView<NakazaStory>("stories", stories) {
            @Override
            protected void populateItem(ListItem<NakazaStory> item) {
                final NakazaStory story = item.getModelObject();

                PageParameters params = new PageParameters();
                params.add("id", story.getId());
                Link storyDetail = new BookmarkablePageLink<BasePage>("storyDetail", StoryDetail.class, params);
                Label storyName = new Label("storyName", Model.of(story.getName()));
                storyDetail.add(storyName);
                item.add(storyDetail);
                final TextField rating = new TextField<Integer>("rating", Model.of(story.getPoints()));
                item.add(rating);

                item.add(new Button("accept") {
                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                        story.setAccepted(true);
                        Integer data = Integer.parseInt((String)rating.getModelObject());
                        story.setPoints(data);
                        storyService.saveOrUpdate(story);
                    }
                });

                item.add(new Button("delete") {
                    @Override
                    public void onSubmit() {
                        super.onSubmit();

                        storyService.delete(story);
                        getList().remove(story);
                    }
                });
            }
        }.setOutputMarkupId(true));


        add(form);
    }
}
