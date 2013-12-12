package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.story.LastAddedStories;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.StoryService;
import org.pilirion.nakaza.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StoryDetail extends BasePage {
    @SpringBean
    StoryService storyService;
    @SpringBean
    UserService userService;

    private boolean amParticipant;

    public StoryDetail(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getStoryButtons()));

        int NONE = -1;
        int id = params.get("id").toInt(NONE);

        NakazaStory story = storyService.getById(id);
        if(story == null) {
            throw new RestartResponseException(AddStory.class);
        }

        amParticipant = false;
        final NakazaUser logged = userService.getLoggedUser();
        if(logged != null) {
            if(logged.getStories().contains(story)){
                amParticipant = true;
            }
        }


        int MAX_STORIES = 5;
        List<NakazaStory> stories = storyService.getLastAdded(MAX_STORIES);
        add(new LastAddedStories("lastAddedStories",stories));

        Label name = new Label("name", Model.of(story.getName()));
        add(name);
        Label descriptionPublic = new Label("descriptionPublic", Model.of(story.getDescriptionPublic()));
        descriptionPublic.setEscapeModelStrings(false);
        add(descriptionPublic);
        Label descriptionPrivate = new Label("descriptionPrivate", Model.of(story.getDescriptionPrivate())){
            @Override
            protected void onConfigure() {
                super.onConfigure();

                setVisibilityAllowed(amParticipant);
            }
        };
        descriptionPrivate.setEscapeModelStrings(false);
        add(descriptionPrivate);

        add(new ListView<NakazaLabel>("labels", story.getLabels()) {
            @Override
            protected void populateItem(ListItem<NakazaLabel> item) {
                item.add(new Label("label",item.getModelObject().getName()));
            }
        });

        add(new ListView<NakazaParticipant>("participants", story.getParticipants()) {
            @Override
            protected void populateItem(ListItem<NakazaParticipant> item) {
                NakazaParticipant participant = item.getModelObject();
                item.add(new Label("group", Model.of(participant.getGroupText())));
                item.add(new Label("descriptionPublic", Model.of(participant.getDescriptionPublic())).setEscapeModelStrings(false));
                final boolean isPrivateVisible = (logged != null) && participant.getUser() != null && participant.getUser().equals(logged);
                item.add(new Label("descriptionPrivate",Model.of(participant.getDescriptionPrivate())){
                    @Override
                    protected void onConfigure() {
                        super.onConfigure();

                        setVisibilityAllowed(isPrivateVisible);
                    }
                }.setEscapeModelStrings(false));

                item.add(new ListView<NakazaLabel>("labels", participant.getLabels()) {
                    @Override
                    protected void populateItem(ListItem<NakazaLabel> item) {
                        item.add(new Label("label",item.getModelObject().getName()));
                    }
                });
            }
        });
    }
}
