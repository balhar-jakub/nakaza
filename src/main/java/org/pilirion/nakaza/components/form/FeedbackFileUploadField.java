package org.pilirion.nakaza.components.form;

import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;

import java.util.List;

/**
 *
 */
public class FeedbackFileUploadField extends FileUploadField {
    private String append = "Feedback";

    public FeedbackFileUploadField(String id, Form form) {
        super(id);
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
