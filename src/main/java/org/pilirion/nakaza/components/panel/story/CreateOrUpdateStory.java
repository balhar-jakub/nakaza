package org.pilirion.nakaza.components.panel.story;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericValidator;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.RepeatableInputPanel;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.pilirion.nakaza.components.form.FeedbackTextArea;
import org.pilirion.nakaza.components.form.FeedbackTextField;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.service.LabelService;
import org.pilirion.nakaza.service.StoryService;
import org.pilirion.nakaza.validator.AtLeastOneParticipantValidator;
import org.pilirion.nakaza.validator.AtLeastOneRequiredValidator;

import java.util.ArrayList;

/**
 *
 */
public class CreateOrUpdateStory extends Panel {
    @SpringBean
    StoryService storyService;
    @SpringBean
    LabelService labelService;

    public CreateOrUpdateStory(String id, NakazaStory story) {
        super(id);

        if(story.getLabels() == null) {
            story.setLabels(new ArrayList<NakazaLabel>());
        }
        if(story.getParticipants() == null) {
            story.setParticipants(new ArrayList<NakazaParticipant>());
        }

        Form<NakazaStory> storyForm = new Form<NakazaStory>("createStory", new CompoundPropertyModel<NakazaStory>(story)){
            @Override
            protected void onSubmit() {
                NakazaStory story =  getModelObject();
                story.setParticipants(((StoryParticipantsPanel) ((FormComponent)get("participants"))).getModelObject());
                storyService.saveOrUpdate(getModelObject());
            }

            @Override
            protected void onError() {
                super.onError();

                System.out.println("Error");
            }

            @Override
            protected void onValidate() {
                super.onValidate();
                System.out.println("There");
            }
        };

        storyForm.add(new FeedbackTextField<String>("name", storyForm).setRequired(true));
        storyForm.add(new FeedbackTextArea("descriptionPublic", storyForm).setRequired(true));
        storyForm.add(new FeedbackTextArea("descriptionPrivate", storyForm).setRequired(true));

        IFactory<NakazaLabel> labelIFactory = new GenericFactory<NakazaLabel>(NakazaLabel.class);
        IValidator<NakazaLabel> labelIValidator = new GenericValidator<NakazaLabel>(labelService);

        RepeatableInputPanel<NakazaLabel> labels = new RepeatableInputPanel<NakazaLabel>("labels", labelIFactory,
                labelIValidator, story.getLabels(), labelService);
        labels.add(new AtLeastOneRequiredValidator());
        storyForm.add(labels);
        storyForm.add(new StoryParticipantsPanel("participants", storyForm).add(new AtLeastOneParticipantValidator()));

        storyForm.add(new Button("submit"));
        add(storyForm);
    }
}
