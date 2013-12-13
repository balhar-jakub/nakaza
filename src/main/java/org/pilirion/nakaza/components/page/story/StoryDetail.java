package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.api.EntityModel;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.form.HidingForm;
import org.pilirion.nakaza.components.form.HidingLabel;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.LabelsPanel;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.participant.CreateOrUpdateParticipantPanel;
import org.pilirion.nakaza.components.panel.participant.ParticipantDetail;
import org.pilirion.nakaza.components.panel.story.LastAddedStories;
import org.pilirion.nakaza.dao.StoryDAO;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.ParticipantService;
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

    @SpringBean
    StoryDAO storyDAO;

    private EntityModel<NakazaStory> storyModel;

    public StoryDetail(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){
        add(new NakazaSignInPanel("signInPanel"));

        int NONE = -1;
        final int id = params.get("id").toInt(NONE);
        final String action = params.get("action").toString(null);
        final int participantId = params.get("participantId").toInt(NONE);

        storyModel = new EntityModel<NakazaStory>(storyService.getDetailOfStory(id), storyDAO);

        List<ButtonLike> lower = Menu.getStoryButtons();
        if(storyService.hasRights(userService.getLoggedUser(), storyModel.getObject())) {
            PageParameters parameters = new PageParameters();
            parameters.add("id", id);
            ButtonLike edit = new ButtonLike("Editovat", CreateStory.class);
            edit.setParams(parameters);
            lower.add(edit);
        }
        add(new LeftMenus("leftMenus", Menu.getMainButtons(), lower));

        int MAX_STORIES = 5;
        List<NakazaStory> stories = storyService.getLastAdded(MAX_STORIES);
        add(new LastAddedStories("lastAddedStories",stories));

        add(new Label("name", Model.of(storyModel.getObject().getName())));
        add(new Label("descriptionPublic", Model.of(storyModel.getObject().getDescriptionPublic())).setEscapeModelStrings(false));
        add(new HidingLabel("descriptionPrivate", Model.of(storyModel.getObject().getDescriptionPrivate()),
                storyService.participates(userService.getLoggedUser(), storyModel.getObject())).setEscapeModelStrings(false));

        add(new LabelsPanel("labelsPanel", storyModel.getObject().getLabels()));

        add(new ListView<NakazaParticipant>("participants", storyModel.getObject().getParticipants()) {
            @Override
            protected void populateItem(ListItem<NakazaParticipant> item) {
                NakazaParticipant participant = item.getModelObject();
                final int participantIdInner = participant.getId();

                boolean isEditFormVisible = !(action != null && action.equals("editParticipant") &&
                    participantId == participantIdInner);
                item.add(new ParticipantDetail("participantDetail", participant, isEditFormVisible));

                Form form = new HidingForm("editForm",
                        storyService.hasRights(userService.getLoggedUser(), storyModel.getObject()) && isEditFormVisible){
                    @Override
                    protected void onSubmit() {
                        super.onSubmit();

                        PageParameters params = new PageParameters();
                        params.add("id", id);
                        params.add("action", "editParticipant");
                        params.add("participantId", participantIdInner);
                        throw new RestartResponseException(StoryDetail.class, params);
                    }
                };
                form.add(new Button("editParticipant"));
                item.add(form);

                item.add(new CreateOrUpdateParticipantPanel("modifyParticipant", storyModel.getObject(), participant,
                        storyService.hasRights(userService.getLoggedUser(), storyModel.getObject()) && !isEditFormVisible));
            }
        });

        boolean isAddParticipantVisible = action != null && action.equals("addParticipant");
        Form form = new HidingForm("addParticipant",
                storyService.hasRights(userService.getLoggedUser(), storyModel.getObject()) && !isAddParticipantVisible) {
            @Override
            protected void onSubmit() {
                super.onSubmit();

                PageParameters params = new PageParameters();
                params.add("id", id);
                params.add("action", "addParticipant");
                throw new RestartResponseException(StoryDetail.class, params);
            }
        };

        form.add(new Button("addParticipantButtton"));
        add(form);

        add(new CreateOrUpdateParticipantPanel("createParticipant", storyModel.getObject(), NakazaParticipant.getEmptyParticipant(),
                storyService.hasRights(userService.getLoggedUser(), storyModel.getObject()) && isAddParticipantVisible));
    }
}
