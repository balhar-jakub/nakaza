package org.pilirion.nakaza.components;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.AddStory;
import org.pilirion.nakaza.components.page.story.AdministerStories;
import org.pilirion.nakaza.components.page.story.CreateStory;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;

import java.util.ArrayList;
import java.util.List;

/**
 * It returns ButtonLikes for different types of pages.
 */
public class Menu {
    public static List<ButtonLike> getMainButtons() {
        List<ButtonLike> upper = new ArrayList<ButtonLike>();
        upper.add(new ButtonLike("Domů", HomePage.class));
        upper.add(new ButtonLike("O hře", AboutGame.class));
        upper.add(new ButtonLike("O světě", AboutWorld.class));
        upper.add(new ButtonLike("Příběhy", StoryList.class));
        upper.add(new ButtonLike("Postavy", CharacterList.class));
        return upper;
    }

    public static List<ButtonLike> getCharacterButtons() {
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        lower.add(new ButtonLike("Nová", CreateCharacter.class));
        return lower;
    }

    public static List<ButtonLike> getStoryButtons() {
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        lower.add(new ButtonLike("Nový", CreateStory.class));
        lower.add(new ButtonLike("Správa", AddStory.class));
        NakazaUser loggedUser = ((NakazaAuthenticatedWebSession) NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(loggedUser != null && loggedUser.getRole() >= NakazaRoles.getRoleByName("Admin")){
            lower.add(new ButtonLike("Admin", AdministerStories.class));
        }

        return lower;
    }

    public static List<ButtonLike> getEmptyButtons() {
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        return lower;
    }
}
