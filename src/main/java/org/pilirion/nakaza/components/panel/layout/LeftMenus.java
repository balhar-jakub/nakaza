package org.pilirion.nakaza.components.panel.layout;

import org.apache.wicket.markup.html.panel.Panel;

import java.util.List;

/**
 *
 */
public class LeftMenus extends Panel {
    public LeftMenus(String id, List<ButtonLike> upper, List<ButtonLike> lower) {
        super(id);

        add(new LeftMenu("leftMenuUpper", upper, "Hlavní menu"));

        add(new LeftMenu("leftMenuLower", lower, "Vedlejší menu"));
    }
}
