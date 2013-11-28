package org.pilirion.nakaza.components.page.statics;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.service.UserService;

/**
 *
 */
public class HomePage extends BasePage{
    @SpringBean
    UserService userService;

    public HomePage(){
        init();
    }

    private void init(){
        add(new ListShortCharacters("listShortChars", userService.getFirstUsersWithCharacters()));
    }
}
