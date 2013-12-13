package org.pilirion.nakaza.components.form;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

/**
 *
 */
public class HidingLabel extends Label {
    private boolean visibility;

    public HidingLabel(String id, IModel<?> model, boolean visibility) {
        super(id, model);

        this.visibility = visibility;
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(visibility);
    }
}
