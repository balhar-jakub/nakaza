package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.form.FeedbackTextArea;
import org.pilirion.nakaza.components.form.FeedbackTextField;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.service.UserService;

/**
 *
 */
public class CreateOrUpdateLogged extends Panel {
    @SpringBean
    UserService userService;
    private NakazaUser user;

    public CreateOrUpdateLogged(String id) {
        super(id);
        user = ((NakazaAuthenticatedWebSession)NakazaAuthenticatedWebSession.get()).getLoggedUser();
        String headerText = "Vytvořit novou postavu";
        int groupId;
        if(user == null) {
            user = NakazaUser.getEmptyUser();
            throw new RestartResponseException(CharacterList.class);
        } else {
            if(user.getCharacter() == null) {
                user.setCharacter(new NakazaCharacter());
                groupId = -1;
            } else  {
                headerText = "Úprava stávající postavy";
                if(user.getCharacter().getGroup() == null) {
                    groupId = -1;
                } else {
                    groupId = Integer.parseInt(user.getCharacter().getGroup());
                }
            }
        }
        Label label = new Label("createCharacterHeader", Model.of(headerText));
        add(label);

        Form<NakazaCharacter> characterForm =
                new Form<NakazaCharacter>("character",new CompoundPropertyModel<NakazaCharacter>(user.getCharacter()))
        {
            @Override
            protected void onSubmit() {
                userService.saveOrUpdate(user);
                PageParameters params = new PageParameters();
                params.add("id", user.getId());

                throw new RestartResponseException(CharacterDetail.class, params);
            }
        };

        characterForm.add(new FeedbackTextField<String>("name", characterForm).setRequired(true));
        characterForm.add(new FeedbackTextField<Integer>("age", characterForm).setRequired(true));

        characterForm.add(new FeedbackSelectPanel("group", characterForm, groupId));
        characterForm.add(new FeedbackTextArea("description", characterForm).setRequired(true));

        characterForm.add(new Button("submit"));

        add(characterForm);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(NakazaAuthenticatedWebSession.get().isSignedIn());
    }
}
