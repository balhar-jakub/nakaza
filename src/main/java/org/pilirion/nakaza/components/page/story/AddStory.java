package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.api.EntityModel;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.dao.UserDAO;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.service.ParticipantService;
import org.pilirion.nakaza.service.StoryService;
import org.pilirion.nakaza.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AddStory extends BasePage {
    @SpringBean
    UserDAO userDAO;

    @SpringBean
    UserService userService;
    @SpringBean
    StoryService storyService;
    @SpringBean
    ParticipantService participantService;

    private EntityModel<NakazaUser> loggedUser;

    public AddStory(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        add(new LeftMenus("leftMenus", Menu.getMainButtons(), Menu.getStoryButtons()));

        loggedUser = new EntityModel<NakazaUser>(userService.getLoggedUser(), userDAO);
        if(loggedUser.getObject() == null){
            throw new RestartResponseException(HomePage.class);
        }
        if(loggedUser.getObject().getCharacter() == null) {
            throw new RestartResponseException(CreateCharacter.class);
        }

        List<NakazaUser> nakazaUsers = userService.getFirstUsersWithCharacters();

        Model<Integer> remainingPoints = Model.of(loggedUser.getObject().getRemainingPoints());
        add(new Label("pointsRemaining", remainingPoints).setOutputMarkupId(true));

        Form form = new Form("storiesForm");
        form.add(new FeedbackPanel("feedback"));
        form.add(new StoryList("availableStories",
                storyService.getAllApproved(Integer.parseInt(loggedUser.getObject().getCharacter().getGroup()))));
        form.add(new StoryListShort("myStories", loggedUser.getObject().getStories()));
        add(form);

        add(new ListShortCharacters("shortCharacters", nakazaUsers));
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forUrl("jquery-ui.min.js"));
        super.renderHead(response);
    }

    private class StoryListShort extends ListView<NakazaStory> {

        public StoryListShort(String id, List<NakazaStory> list) {
            super(id, list);
            setOutputMarkupId(true);
        }

        @Override
        protected void populateItem(ListItem<NakazaStory> item) {
            final NakazaStory story = item.getModelObject();
            int storyPoints = ((story.getPoints() == null) ? 0 : story.getPoints());
            item.add(new Label("storyPoints", storyPoints));

            PageParameters params = new PageParameters();
            params.add("id", story.getId());
            Link storyDetailLink = new BookmarkablePageLink<CharacterDetail>("storyDetail", CharacterDetail.class, params);
            storyDetailLink.add(new Label("story", story.getName()));
            item.add(storyDetailLink);

            item.add(new AjaxButton("change") {}.add(new AjaxFormComponentUpdatingBehavior("click") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    for(NakazaParticipant participant: story.getParticipants()){
                        if(participant.getUser() == loggedUser.getObject()){
                            participant.setUser(null);
                            participantService.saveOrUpdate(participant);
                        }
                    }
                    loggedUser.getObject().getStories().remove(story);

                    int remainingPoints = ((loggedUser.getObject().getRemainingPoints() != null) ? loggedUser.getObject().getRemainingPoints(): 0);
                    int storyPoints = ((story.getPoints() == null) ? 0 : story.getPoints());
                    loggedUser.getObject().setRemainingPoints(remainingPoints + storyPoints);
                    userService.saveOrUpdate(loggedUser.getObject());

                    target.add(AddStory.this);
                }
            }));
        }
    }

    private class StoryList extends ListView<NakazaStory> {

        public StoryList(String id, List<NakazaStory> list) {
            super(id, list);
            setOutputMarkupId(true);
        }

        @Override
        protected void populateItem(ListItem<NakazaStory> item) {
            final NakazaStory story = item.getModelObject();
            int storyPoints = ((story.getPoints() == null) ? 0 : story.getPoints());
            item.add(new Label("storyPoints", storyPoints));

            PageParameters params = new PageParameters();
            params.add("id", story.getId());
            Link storyDetailLink = new BookmarkablePageLink<CharacterDetail>("storyDetail", CharacterDetail.class, params);
            storyDetailLink.add(new Label("story", story.getName()));
            item.add(storyDetailLink);

            List<NakazaParticipant> participants = new ArrayList<NakazaParticipant>();
            for(NakazaParticipant participant: story.getParticipants()) {
                if(participant.getGroup().equals(loggedUser.getObject().getCharacter().getGroup())){
                    participants.add(participant);
                }
            }

            item.add(new ListView<NakazaParticipant>("participants", participants) {
                @Override
                protected void populateItem(ListItem<NakazaParticipant> item) {
                    final NakazaParticipant participant = item.getModelObject();
                    item.add(new Label("participantDescription", Model.of(participant.getDescriptionPublic())).
                            setEscapeModelStrings(false));

                    item.add(new AjaxButton("change") {}.add(new AjaxFormComponentUpdatingBehavior("click") {
                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            int remainingPoints = ((loggedUser.getObject().getRemainingPoints() != null) ? loggedUser.getObject().getRemainingPoints(): 0);
                            int storyPoints = ((story.getPoints() == null) ? 0 : story.getPoints());
                            if(remainingPoints > storyPoints) {
                                participant.setUser(loggedUser.getObject());
                                loggedUser.getObject().getStories().add(story);
                                getList().remove(story);
                                loggedUser.getObject().setRemainingPoints(remainingPoints - storyPoints);

                                participantService.saveOrUpdate(participant);
                                userService.saveOrUpdate(loggedUser.getObject());
                            } else {
                                error("You do not have enough points left for this story.");
                            }

                            target.add(AddStory.this);
                        }
                    }));
                }
            }.setOutputMarkupId(true));
        }
    }
}
