package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 */
public class FeedbackSelectPanel extends FormComponentPanel<Integer> {
    private String append = "Feedback";
    private IModel<SelectOption> selectModel;

    public FeedbackSelectPanel(String id, Form form) {
        super(id);

        SelectOption[] options = new SelectOption[] {
                new SelectOption("0", "Zombie"),
                new SelectOption("1", "Přeživší"),
                new SelectOption("2", "Armáda")
        };
        ChoiceRenderer<SelectOption> choiceRenderer = new ChoiceRenderer<SelectOption>("value", "key");

        selectModel = new IModel<SelectOption>() {
            private SelectOption object;

            @Override
            public SelectOption getObject() {
                return object;
            }

            @Override
            public void setObject(SelectOption object) {
                this.object = object;
            }

            @Override
            public void detach() {
            }
        };
        add(new DropDownChoice<SelectOption>("group", selectModel, Arrays.asList(options), choiceRenderer));

        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));
    }

    @Override
    protected void convertInput() {
        super.convertInput();

        SelectOption option = (SelectOption)((FormComponent) get("group")).getConvertedInput();
        setConvertedInput(Integer.parseInt(option.getKey()));
    }

    private class SelectOption implements Serializable {
        private String key;
        private String value;

        private SelectOption(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
