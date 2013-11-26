package org.pilirion.nakaza.components.form;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;

/**
 *
 */
public class FeedbackDateTextField extends DateTextField {
    private String append = "Feedback";

    public FeedbackDateTextField(String id, String datePattern, Form form) {
        super(id, datePattern);
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
