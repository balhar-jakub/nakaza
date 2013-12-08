package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.story.AdministerPanel;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.StoryService;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class AdministerStories extends BasePage {
    @SpringBean
    StoryService storyService;

    public AdministerStories(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        List<ButtonLike> upper = new ArrayList<ButtonLike>();
        upper.add(new ButtonLike("Domů", HomePage.class));
        upper.add(new ButtonLike("O hře", AboutGame.class));
        upper.add(new ButtonLike("O světě", AboutWorld.class));
        upper.add(new ButtonLike("Příběhy", StoryList.class));
        upper.add(new ButtonLike("Postavy", CharacterList.class));
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        lower.add(new ButtonLike("Nový", CreateStory.class));
        lower.add(new ButtonLike("Správa", AddStory.class));
        NakazaUser loggedUser = ((NakazaAuthenticatedWebSession) NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(loggedUser != null && loggedUser.getRole() >= NakazaRoles.getRoleByName("Admin")){
            lower.add(new ButtonLike("Admin", AdministerStories.class));
        }
        add(new LeftMenus("leftMenus", upper, lower));

        add(new AdministerPanel("stories", storyService.getAll()));
    }
}
