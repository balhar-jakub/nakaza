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
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;

import java.util.List;

/**
 *
 */
public class ListStoriesPanel extends Panel {
    public ListStoriesPanel(String id, List<NakazaStory> stories) {
        super(id);

        final NakazaUser logged = ((NakazaAuthenticatedWebSession)NakazaAuthenticatedWebSession.get()).getLoggedUser();
        add(new ListView<NakazaStory>("stories", stories) {
            @Override
            protected void populateItem(ListItem<NakazaStory> item) {
                final NakazaStory story = item.getModelObject();

                PageParameters params = new PageParameters();
                params.add("id",story.getId());
                Link storyDetail = new BookmarkablePageLink<BasePage>("storyDetail", StoryDetail.class, params);
                Label storyName = new Label("storyName", Model.of(story.getName()));
                storyDetail.add(storyName);
                item.add(storyDetail);

                item.add(new Label("descriptionPublic",Model.of(story.getDescriptionPublic())).setEscapeModelStrings(false));
                item.add(new Label("descriptionPrivate",Model.of(story.getDescriptionPrivate())){
                    @Override
                    protected void onConfigure() {
                        super.onConfigure();

                        setVisibilityAllowed((logged != null) && story.getUsers().contains(logged));
                    }
                }.setEscapeModelStrings(false));

                boolean amParticipant = (logged != null) && story.getUsers().contains(logged);
                NakazaParticipant me = null;
                if(amParticipant) {
                    List<NakazaParticipant> participants = story.getParticipants();
                    for(NakazaParticipant participant: participants){
                        if(participant.getUser().equals(logged)){
                            me = participant;
                            break;
                        }
                    }
                    if(me == null) {
                        me = NakazaParticipant.getEmptyParticipant();
                    }
                } else {
                    me = NakazaParticipant.getEmptyParticipant();
                }

                item.add(new Label("descriptionPrivateRole", Model.of(me.getDescriptionPrivate())){
                    @Override
                    protected void onConfigure() {
                        super.onConfigure();

                        setVisibilityAllowed((logged != null) && story.getUsers().contains(logged));
                    }
                }.setEscapeModelStrings(false));

                item.add(new ListView<NakazaLabel>("labels", story.getLabels()) {
                    @Override
                    protected void populateItem(ListItem<NakazaLabel> item) {
                        item.add(new Label("label", item.getModelObject().getName()));
                    }
                });

                item.add(new ListView<NakazaParticipant>("participants", story.getParticipants()) {
                    @Override
                    protected void populateItem(ListItem<NakazaParticipant> item) {
                        item.add(new Label("participant", item.getModelObject().getDescriptionPublic()).setEscapeModelStrings(false));
                    }
                });
            }
        });
    }
}
