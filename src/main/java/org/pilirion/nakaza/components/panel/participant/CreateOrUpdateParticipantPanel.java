package org.pilirion.nakaza.components.panel.participant;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericValidator;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.RepeatableInputPanel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.pilirion.nakaza.api.EntityModel;
import org.pilirion.nakaza.components.form.FeedbackTextArea;
import org.pilirion.nakaza.components.form.FeedbackTextField;
import org.pilirion.nakaza.components.page.story.StoryDetail;
import org.pilirion.nakaza.components.panel.character.FeedbackSelectPanel;
import org.pilirion.nakaza.dao.ParticipantDAO;
import org.pilirion.nakaza.dao.StoryDAO;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.service.LabelService;
import org.pilirion.nakaza.service.StoryService;
import org.pilirion.nakaza.validator.AtLeastOneRequiredValidator;

/**
 *
 */
public class CreateOrUpdateParticipantPanel extends Panel {
    @SpringBean
    ParticipantDAO participantDAO;
    @SpringBean
    StoryDAO storyDAO;

    @SpringBean
    StoryService storyService;
    @SpringBean
    LabelService labelService;

    private boolean show;

    private EntityModel<NakazaStory> storyModel;

    public CreateOrUpdateParticipantPanel(String id, NakazaStory story, NakazaParticipant participant, boolean show) {
        super(id);
        this.show = show;
        storyModel = new EntityModel<NakazaStory>(story, storyDAO);

        Form<NakazaParticipant> participantForm = new Form<NakazaParticipant>("createParticipant",
                new CompoundPropertyModel<NakazaParticipant>(participant)){
            @Override
            protected void onSubmit() {
                NakazaStory story =  storyModel.getObject();
                NakazaParticipant participant = getModelObject();
                if(participant.getId() == 0) {
                    participant.setStory(story);
                    story.getParticipants().add(participant);
                }
                storyService.saveOrUpdate(story);

                PageParameters params = new PageParameters();
                params.add("id", story.getId());
                throw new RestartResponseException(StoryDetail.class, params);
            }
        };

        participantForm.add(new FeedbackTextField<String>("name", participantForm).setRequired(true));
        participantForm.add(new FeedbackTextArea("descriptionPublic", participantForm).setRequired(true));
        participantForm.add(new FeedbackTextArea("descriptionPrivate", participantForm).setRequired(true));

        IFactory<NakazaLabel> labelIFactory = new GenericFactory<NakazaLabel>(NakazaLabel.class);
        IValidator<NakazaLabel> labelIValidator = new GenericValidator<NakazaLabel>(labelService);

        RepeatableInputPanel<NakazaLabel> labels = new RepeatableInputPanel<NakazaLabel>("labels", labelIFactory,
                labelIValidator, participant.getLabels(), labelService);
        labels.add(new AtLeastOneRequiredValidator());
        participantForm.add(labels);

        int group = (participant.getGroup() != null) ? Integer.parseInt(participant.getGroup()) : -1;
        participantForm.add(new FeedbackSelectPanel("group", participantForm, group));

        participantForm.add(new Button("submit"));

        add(participantForm);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(show);
    }
}
