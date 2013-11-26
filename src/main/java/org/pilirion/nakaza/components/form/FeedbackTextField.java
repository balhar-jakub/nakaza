package org.pilirion.nakaza.components.form;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;

/**
 *
 */
public class FeedbackTextField<T> extends TextField<T> {
    private String append = "Feedback";

    public FeedbackTextField(String id, Form form) {
        super(id);

        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));
    }
}
