package org.pilirion.nakaza.components.form;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;

/**
 *
 */
public class FeedbackPasswordField extends PasswordTextField {
    private String append = "Feedback";

    public FeedbackPasswordField(String id, Form form) {
        super(id);
        init(id, form);
    }

    public FeedbackPasswordField(String id, IModel<String> model, Form form) {
        super(id, model);
        init(id, form);
    }

    private void init(String id, Form form){
        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));
    }
}
