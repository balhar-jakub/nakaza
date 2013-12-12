package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.components.panel.story.CreateOrUpdateStory;
import org.pilirion.nakaza.components.panel.story.LastAddedStories;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.StoryService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CreateStory extends BasePage {
    @SpringBean
    StoryService storyService;

    public CreateStory(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getStoryButtons()));

        int NONE = -1;
        int actualStory = params.get("id").toInt(NONE);

        NakazaStory story;
        if(actualStory == NONE) {
            story = NakazaStory.getEmptyStory();
        } else {
            story = storyService.getById(actualStory);
            if(story == null) {
                story = NakazaStory.getEmptyStory();
            }
        }

        add(new CreateOrUpdateStory("createStory", story));
        int MAX_STORIES = 5;
        List<NakazaStory> stories = storyService.getLastAdded(MAX_STORIES);
        add(new LastAddedStories("lastAddedStories", stories));
    }
}
