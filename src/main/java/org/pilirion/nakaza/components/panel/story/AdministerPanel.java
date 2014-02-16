package org.pilirion.nakaza.components.panel.story;

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
import org.pilirion.nakaza.components.form.BookmarkableLinkWithLabel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.story.StoryDetail;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.service.ParticipantService;
import org.pilirion.nakaza.service.StoryService;

import java.util.List;

/**
 *
 */
public class AdministerPanel extends Panel {
    @SpringBean
    StoryService storyService;
    @SpringBean
    ParticipantService participantService;

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
                item.add(new BookmarkableLinkWithLabel("storyDetail", StoryDetail.class, Model.of(story.getName()), Model.of(params)));

                String accepted = story.getAccepted() ? "Schváleno" : "Čeká";
                item.add(new Label("state", Model.of(accepted)));

                item.add(new Button("accept") {
                    @Override
                    public void onSubmit() {
                        super.onSubmit();
                        story.setAccepted(true);
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

                item.add(new ListView<NakazaParticipant>("participants", story.getParticipants()) {
                    @Override
                    protected void populateItem(ListItem<NakazaParticipant> item) {
                        final NakazaParticipant participant = item.getModelObject();
                        item.add(new Label("name", Model.of(participant.getName())));

                        final TextField<String> points = new TextField<String>("points", Model.of(String.valueOf(participant.getPoints())));
                        item.add(points);

                        item.add(new Button("submit"){
                            @Override
                            public void onSubmit() {
                                super.onSubmit();

                                participant.setPoints(Integer.parseInt(points.getModelObject()));
                                participantService.saveOrUpdate(participant);
                            }
                        });
                    }
                });
            }
        }.setOutputMarkupId(true));


        add(form);
    }
}
