package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.panel.story.AddStoryToCharacter;
import org.pilirion.nakaza.components.panel.story.CreateStoryPanel;
import org.pilirion.nakaza.components.panel.story.ListStoriesPanel;
import org.pilirion.nakaza.service.StoryService;

/**
 *
 */
public class StoryList extends BasePage {
    @SpringBean
    StoryService storyService;

    public StoryList() {
        add(new ListStoriesPanel("storiesPanel", storyService.getAll()));

        add(new BookmarkablePageLink<BasePage>("login",Login.class));

        add(new CreateStoryPanel("storiesCreation"));

        add(new AddStoryToCharacter("addStoriesToCharacter"));
    }
}
