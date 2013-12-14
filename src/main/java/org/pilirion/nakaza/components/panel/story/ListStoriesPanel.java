package org.pilirion.nakaza.components.panel.story;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.form.HidingLabel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.story.StoryDetail;
import org.pilirion.nakaza.components.panel.LabelsPanel;
import org.pilirion.nakaza.components.panel.participant.ParticipantShortPanel;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.service.UserService;

import java.util.List;

/**
 *
 */
public class ListStoriesPanel extends Panel {
    @SpringBean
    UserService userService;

    public ListStoriesPanel(String id, List<NakazaStory> stories) {
        super(id);

        add(new ListView<NakazaStory>("stories", stories) {
            @Override
            protected void populateItem(ListItem<NakazaStory> item) {
                NakazaStory story = item.getModelObject();
                NakazaUser logged = userService.getLoggedUser();

                PageParameters params = new PageParameters();
                params.add("id",story.getId());
                Link storyDetail = new BookmarkablePageLink<BasePage>("storyDetail", StoryDetail.class, params);
                Label storyName = new Label("storyName", Model.of(story.getName()));
                storyDetail.add(storyName);
                item.add(storyDetail);

                boolean isPrivateShown = (logged != null) && story.getUsers().contains(logged);
                item.add(new Label("descriptionPublic",Model.of(story.getDescriptionPublic())).setEscapeModelStrings(false));
                item.add(new HidingLabel("descriptionPrivate",
                        Model.of(story.getDescriptionPrivate()), isPrivateShown).setEscapeModelStrings(false));

                boolean amParticipant = (logged != null) && story.getUsers().contains(logged);
                NakazaParticipant me = null;
                if(amParticipant) {
                    List<NakazaParticipant> participants = story.getParticipants();
                    for(NakazaParticipant participant: participants){
                        if(participant.getUser() != null && participant.getUser().equals(logged)){
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

                item.add(new HidingLabel("descriptionPrivateRole",
                        Model.of(me.getDescriptionPrivate()), isPrivateShown).setEscapeModelStrings(false));

                item.add(new LabelsPanel("labelsPanel", story.getLabels()));

                item.add(new ParticipantShortPanel("participantsPanel", story.getParticipants()));
            }
        });
    }
}
