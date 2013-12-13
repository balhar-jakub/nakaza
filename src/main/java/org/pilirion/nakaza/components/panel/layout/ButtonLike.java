package org.pilirion.nakaza.components.panel.layout;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;

import java.io.Serializable;

/**
 *
 */
public class ButtonLike implements Serializable {
    private String buttonText;
    private Class<? extends BasePage> linkClass;
    private PageParameters params;

    public ButtonLike(String buttonText, Class<? extends BasePage> linkClass) {
        this.buttonText = buttonText;
        this.linkClass = linkClass;
    }

    public String getButtonText() {
        return buttonText;
    }

    public Class<? extends BasePage> getLinkClass() {
        return linkClass;
    }

    public PageParameters getParams() {
        return params;
    }

    public void setParams(PageParameters params) {
        this.params = params;
    }
}
