package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.panel.Panel;
import org.pilirion.nakaza.entity.NakazaUser;

/**
 *
 */
public class ZombiePanel extends Panel {
    public ZombiePanel(String id, NakazaUser user) {
        super(id);

        if(user != null && user.getCharacter() != null && user.getCharacter().getGroup() != null &&
                user.getCharacter().getGroup().equals("0")) {
            setVisibilityAllowed(true);
        } else {
            setVisibilityAllowed(false);
        }
    }
}
