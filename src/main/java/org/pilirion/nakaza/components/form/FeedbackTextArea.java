package org.pilirion.nakaza.components.form;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;
import org.pilirion.nakaza.entity.NakazaCharacter;
import wicket.contrib.tinymce.TinyMceBehavior;

import javax.persistence.criteria.From;

/**
 *
 */
public class FeedbackTextArea extends TextArea {
    public FeedbackTextArea(String id, Form form, String append){
        super(id);

        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));

        add(new TinyMceBehavior());
    }

    public FeedbackTextArea(String id, ListItem form, String append){
        super(id);

        ComponentFeedbackMessageFilter filter = new ComponentFeedbackMessageFilter(this);
        final FeedbackPanel feedbackPanel = new FeedbackPanel(id + append, filter);
        feedbackPanel.setOutputMarkupId(true);
        form.add(feedbackPanel);
        add(new AjaxFeedbackUpdatingBehavior("blur", feedbackPanel));

        add(new TinyMceBehavior());
    }

    public FeedbackTextArea(String id, Form form) {
        this(id, form, "Feedback");
    }
}
