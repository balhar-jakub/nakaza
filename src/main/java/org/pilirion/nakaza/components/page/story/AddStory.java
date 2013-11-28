package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.service.StoryService;
import org.pilirion.nakaza.service.UserService;

import java.util.List;

/**
 *
 */
public class AddStory extends BasePage {
    @SpringBean
    UserService userService;
    @SpringBean
    StoryService storyService;

    private Model<Integer> remainingPoints;
    private NakazaUser loggedUser;

    public AddStory(){
        init();
    }

    private void init(){
        loggedUser = ((NakazaAuthenticatedWebSession) NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(loggedUser == null){
            throw new RestartResponseException(HomePage.class);
        }
        if(loggedUser.getCharacter() == null) {
            throw new RestartResponseException(CreateCharacter.class);
        }

        List<NakazaUser> nakazaUsers = userService.getFirstUsersWithCharacters();

        remainingPoints = Model.of(loggedUser.getRemainingPoints());
        add(new Label("pointsRemaining", remainingPoints).setOutputMarkupId(true));

        Form form = new Form("storiesForm");

        form.add(new FeedbackPanel("feedback"));

        form.add(new StoryList("availableStories", storyService.getAll(), false));
        form.add(new StoryList("myStories", loggedUser.getStories(), true));

        add(form);

        add(new ListShortCharacters("shortCharacters", nakazaUsers));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forUrl("jquery-ui.min.js"));
        super.renderHead(response);
    }

    private class StoryList extends ListView<NakazaStory> {
        private boolean remove;

        public StoryList(String id, List<NakazaStory> list, boolean remove) {
            super(id, list);
            setOutputMarkupId(true);
            this.remove = remove;
        }

        @Override
        protected void populateItem(ListItem<NakazaStory> item) {
            final NakazaStory story = item.getModelObject();
            item.add(new Label("story", story.getName()));

            item.add(new AjaxButton("change") {}.add(new AjaxFormComponentUpdatingBehavior("click") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    if(loggedUser.getRemainingPoints() > story.getPoints()) {
                        if(remove) {
                            loggedUser.getStories().remove(story);
                            loggedUser.setRemainingPoints(loggedUser.getRemainingPoints() + story.getPoints());
                        } else {
                            loggedUser.getStories().add(story);
                            loggedUser.setRemainingPoints(loggedUser.getRemainingPoints() - story.getPoints());
                        }
                        userService.saveOrUpdate(loggedUser);
                    } else {
                        error("You do not have enough points left for this story.");
                    }

                    target.add(AddStory.this);
                }
            }));
        }
    }
}
