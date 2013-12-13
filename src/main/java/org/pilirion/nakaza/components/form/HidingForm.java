package org.pilirion.nakaza.components.form;

import org.apache.wicket.markup.html.form.Form;

/**
 *
 */
public class HidingForm extends Form {
    private Boolean isVisible;

    public HidingForm(String id, Boolean isVisible) {
        super(id);

        this.isVisible = isVisible;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(isVisible);
    }
}
