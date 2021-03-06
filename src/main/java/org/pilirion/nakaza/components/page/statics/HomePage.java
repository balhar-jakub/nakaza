package org.pilirion.nakaza.components.page.statics;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.service.UserService;

/**
 *
 */
public class HomePage extends BasePage{
    @SpringBean
    UserService userService;

    public HomePage(){
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getEmptyButtons()));

        add(new ListShortCharacters("listShortChars", userService.getMostRecentWithCharacters()));

        add(new Image("disease1", new PackageResourceReference(BasePage.class, "img/disease1.png")));
        add(new Image("disease2", new PackageResourceReference(BasePage.class, "img/disease2.png")));
    }
}
