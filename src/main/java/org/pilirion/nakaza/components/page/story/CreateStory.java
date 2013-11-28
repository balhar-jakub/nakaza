package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.panel.story.CreateOrUpdateStory;
import org.pilirion.nakaza.components.panel.story.LastAddedStories;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.service.StoryService;

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
