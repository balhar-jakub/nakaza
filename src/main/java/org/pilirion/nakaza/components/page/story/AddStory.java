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
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.panel.character.ListShortCharacters;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
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
    UserService userService;
    @SpringBean
    StoryService storyService;
    @SpringBean
    ParticipantService participantService;

    private Model<Integer> remainingPoints;
    private NakazaUser loggedUser;

    public AddStory(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        List<ButtonLike> upper = new ArrayList<ButtonLike>();
        upper.add(new ButtonLike("Domů", HomePage.class));
        upper.add(new ButtonLike("O hře", AboutGame.class));
        upper.add(new ButtonLike("O světě", AboutWorld.class));
        upper.add(new ButtonLike("Příběhy", org.pilirion.nakaza.components.page.story.StoryList.class));
        upper.add(new ButtonLike("Postavy", CharacterList.class));
        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        lower.add(new ButtonLike("Nový", CreateStory.class));
        lower.add(new ButtonLike("Správa", AddStory.class));
        loggedUser = ((NakazaAuthenticatedWebSession) NakazaAuthenticatedWebSession.get()).getLoggedUser();
        if(loggedUser != null && loggedUser.getRole() >= NakazaRoles.getRoleByName("Admin")){
            lower.add(new ButtonLike("Admin", AdministerStories.class));
        }
        add(new LeftMenus("leftMenus", upper, lower));

        if(loggedUser == null){
            throw new RestartResponseException(HomePage.class);
        }
        if(loggedUser.getCharacter() == null) {
            throw new RestartResponseException(CreateCharacter.class);
        }

        loggedUser = userService.getById(loggedUser.getId());
        List<NakazaUser> nakazaUsers = userService.getFirstUsersWithCharacters();

        remainingPoints = Model.of(loggedUser.getRemainingPoints());
        add(new Label("pointsRemaining", remainingPoints).setOutputMarkupId(true));

        Form form = new Form("storiesForm");

        form.add(new FeedbackPanel("feedback"));

        form.add(new StoryList("availableStories", storyService.getAllApproved(Integer.parseInt(loggedUser.getCharacter().getGroup()))));
        form.add(new StoryListShort("myStories", loggedUser.getStories()));

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
                        if(participant.getUser() == loggedUser){
                            participant.setUser(null);
                            participantService.saveOrUpdate(participant);
                        }
                    }
                    loggedUser.getStories().remove(story);

                    int remainingPoints = ((loggedUser.getRemainingPoints() != null) ? loggedUser.getRemainingPoints(): 0);
                    int storyPoints = ((story.getPoints() == null) ? 0 : story.getPoints());
                    loggedUser.setRemainingPoints(remainingPoints + storyPoints);
                    userService.saveOrUpdate(loggedUser);

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
                if(participant.getGroup().equals(loggedUser.getCharacter().getGroup())){
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
                            int remainingPoints = ((loggedUser.getRemainingPoints() != null) ? loggedUser.getRemainingPoints(): 0);
                            int storyPoints = ((story.getPoints() == null) ? 0 : story.getPoints());
                            if(remainingPoints > storyPoints) {
                                participant.setUser(loggedUser);
                                loggedUser.getStories().add(story);
                                getList().remove(story);
                                loggedUser.setRemainingPoints(remainingPoints - storyPoints);

                                participantService.saveOrUpdate(participant);
                                userService.saveOrUpdate(loggedUser);
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
