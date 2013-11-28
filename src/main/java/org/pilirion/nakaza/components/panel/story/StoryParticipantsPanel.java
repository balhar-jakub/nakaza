package org.pilirion.nakaza.components.panel.story;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.GenericValidator;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IFactory;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.RepeatableInputPanel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidator;
import org.pilirion.nakaza.components.form.FeedbackTextArea;
import org.pilirion.nakaza.components.panel.character.FeedbackSelectPanel;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.service.LabelService;
import org.pilirion.nakaza.validator.AtLeastOneRequiredValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class StoryParticipantsPanel extends FormComponentPanel<ArrayList<NakazaParticipant>> {
    private Form form;
    @SpringBean
    LabelService labelService;

    public StoryParticipantsPanel(String id, Form form) {
        super(id);
        this.form = form;

        ArrayList<NakazaParticipant> participants = getModelObject();
        if(participants == null) {
            participants = new ArrayList<NakazaParticipant>();
            IModel<ArrayList<NakazaParticipant>> participantsModel = new IModel<ArrayList<NakazaParticipant>>() {
                private ArrayList<NakazaParticipant> object;

                @Override
                public ArrayList<NakazaParticipant> getObject() {
                    return object;
                }

                @Override
                public void setObject(ArrayList<NakazaParticipant> object) {
                    this.object = object;
                }

                @Override
                public void detach() {

                }
            };
            participantsModel.setObject(participants);
            setModel(participantsModel);
        }
        if(participants.isEmpty()){
            participants.add(NakazaParticipant.getEmptyParticipant());
        }

        setOutputMarkupId(true);
        add(new RepeatableListView<NakazaParticipant>("participants", participants));
        add(new AjaxButton("addParticipant"){}.add(new AjaxFormComponentUpdatingBehavior("click") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                RepeatableListView view = (RepeatableListView) get("participants");
                view.getList().add(NakazaParticipant.getEmptyParticipant());

                target.add(StoryParticipantsPanel.this);
            }
        }).setOutputMarkupId(true));
    }

    @Override
    protected void convertInput() {
        super.convertInput();

        ArrayList<NakazaParticipant> participants = getModelObject();

        List<ArrayList<FormComponent>> inputs = ((RepeatableListView)get("participants")).inputs;
        int act = 0;
        for(ArrayList<FormComponent> input: inputs) {
            NakazaParticipant participant = participants.get(act);

            participant.setDescriptionPublic((String)input.get(0).getConvertedInput());
            participant.setDescriptionPrivate((String)input.get(1).getConvertedInput());
            participant.setGroup(String.valueOf(input.get(2).getConvertedInput()));
            participant.setLabels((List<NakazaLabel>)input.get(3).getConvertedInput());

            act++;
        }

        setConvertedInput(participants);
        getModel().setObject(participants);
    }

    private class RepeatableListView<T extends NakazaParticipant> extends ListView<T> {

        private List<ArrayList<FormComponent>> inputs = new ArrayList<ArrayList<FormComponent>>();

        public RepeatableListView(String id, List<T> data) {
            super(id, data);

            setReuseItems(true);
            setOutputMarkupId(true);
        }

        @Override
        protected void populateItem(ListItem<T> item) {
            T participant = item.getModelObject();
            ArrayList<FormComponent> oneInputs = new ArrayList<FormComponent>();

            FormComponent descriptionPublic = new FeedbackTextArea("descriptionPublic", item, "Feedback");
            oneInputs.add(descriptionPublic);
            item.add(descriptionPublic);

            FormComponent descriptionPrivate = new FeedbackTextArea("descriptionPrivate", item, "Feedback");
            oneInputs.add(descriptionPrivate);
            item.add(descriptionPrivate);

            FormComponent group = new FeedbackSelectPanel("group", item);
            oneInputs.add(group);
            item.add(group);

            IFactory<NakazaLabel> labelIFactory = new GenericFactory<NakazaLabel>(NakazaLabel.class);
            IValidator<NakazaLabel> labelIValidator = new GenericValidator<NakazaLabel>(labelService);

            RepeatableInputPanel<NakazaLabel> labels = new RepeatableInputPanel<NakazaLabel>("labels", labelIFactory,
                    labelIValidator, participant.getLabels(), labelService);
            labels.add(new AtLeastOneRequiredValidator());
            oneInputs.add(labels);
            item.add(labels);
            inputs.add(oneInputs);
        }
    }
}
