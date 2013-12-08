package org.pilirion.nakaza.components.panel.layout;

import org.pilirion.nakaza.components.page.BasePage;

import java.io.Serializable;

/**
 *
 */
public class ButtonLike implements Serializable {
    private String buttonText;
    private Class<? extends BasePage> linkClass;

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
}
