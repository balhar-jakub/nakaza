package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.story.AdministerPanel;
import org.pilirion.nakaza.service.StoryService;


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
        add(new AdministerPanel("stories", storyService.getAll()));
    }
}
