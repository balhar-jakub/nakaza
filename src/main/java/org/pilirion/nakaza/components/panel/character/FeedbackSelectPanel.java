package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;
import org.pilirion.nakaza.components.panel.story.StoryParticipantsPanel;
import org.pilirion.nakaza.service.UserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class FeedbackSelectPanel extends FormComponentPanel<Integer> {
    private String append = "Feedback";
    private IModel<SelectOption> selectModel;
    @SpringBean
    private UserService userService;

    public FeedbackSelectPanel(String id, Form form, int chosen) {
        super(id);

        SelectOption[] options = getAvailableOptions();
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
        if(chosen != -1) {
            selectModel.setObject(options[chosen]);
        }
        add(new DropDownChoice<SelectOption>("group", selectModel, Arrays.asList(options), choiceRenderer));

        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));
    }

    public FeedbackSelectPanel(String id, ListItem form) {
        super(id);

        SelectOption[] options = getAvailableOptions();
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
        if(option != null) {
            setConvertedInput(Integer.parseInt(option.getKey()));
        } else {
            setConvertedInput(null);
        }
    }

    protected SelectOption[] getAvailableOptions(){
        List<SelectOption> available = new ArrayList<SelectOption>();
        available.add(new SelectOption("0", "Zombie"));
        available.add(new SelectOption("1", "Přeživší"));
        available.add(new SelectOption("2", "Armáda"));
        return available.toArray(new SelectOption[]{});
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
