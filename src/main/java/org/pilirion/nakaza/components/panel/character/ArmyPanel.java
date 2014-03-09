package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.entity.NakazaUser;

/**
 *
 */
public class ArmyPanel extends Panel {
    public ArmyPanel(String id, NakazaUser user) {
        super(id);

        if(user.getCharacter() != null && user.getCharacter().getGroup() != null &&
                user.getCharacter().getGroup().equals("2")) {
            setVisibilityAllowed(true);
        } else {
            setVisibilityAllowed(false);
        }

        add(new Image("map", new PackageResourceReference(BasePage.class, "img/map.png")));
    }
}
