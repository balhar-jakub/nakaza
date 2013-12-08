package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.Nakaza;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.story.ListStoriesPanel;
import org.pilirion.nakaza.components.panel.story.ListStoriesShortPanel;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CharacterDetail extends BasePage {
    @SpringBean
    UserService userService;

    public CharacterDetail(PageParameters params) {
        add(new NakazaSignInPanel("signInPanel"));

        List<ButtonLike> upper = new ArrayList<ButtonLike>();
        upper.add(new ButtonLike("Domů", HomePage.class));
        upper.add(new ButtonLike("O hře", AboutGame.class));
        upper.add(new ButtonLike("O světě", AboutWorld.class));
        upper.add(new ButtonLike("Příběhy", StoryList.class));
        upper.add(new ButtonLike("Postavy", CharacterList.class));
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        lower.add(new ButtonLike("Nová", CreateCharacter.class));
        add(new LeftMenus("leftMenus", upper, lower));

        int NONE = -1;
        int id = params.get("id").toInt(NONE);

        NakazaUser user = userService.getById(id);
        if(id == NONE || user == null || user.getCharacter() == null) {
            throw new RestartResponseException(CharacterList.class);
        }
        NakazaCharacter character = user.getCharacter();

        add(new Image("avatarImage", new PackageResourceReference(Nakaza.class, user.getImage())));

        add(new Label("name", character.getName()));
        add(new Label("age", character.getAge()));
        add(new Label("descriptionPublic", character.getDescription()).setEscapeModelStrings(false));

        add(new ListStoriesPanel("listStories", user.getStories()));
        add(new ListStoriesShortPanel("shortStories", user.getStories()));
    }
}
