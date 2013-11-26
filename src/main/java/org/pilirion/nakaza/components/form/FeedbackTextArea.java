package org.pilirion.nakaza.components.form;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;
import org.pilirion.nakaza.entity.NakazaCharacter;
import wicket.contrib.tinymce.TinyMceBehavior;

/**
 *
 */
public class FeedbackTextArea extends TextArea {
    private String append = "Feedback";

    public FeedbackTextArea(String id, Form<NakazaCharacter> form) {
        super(id);

        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));

        add(new TinyMceBehavior());
    }
}
