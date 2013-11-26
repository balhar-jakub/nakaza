package org.pilirion.nakaza.components.panel.character;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.form.FeedbackTextArea;
import org.pilirion.nakaza.components.form.FeedbackTextField;
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

    public CreateOrUpdateLogged(String id, NakazaUser pUser) {
        super(id);
        this.user = pUser;

        Form<NakazaCharacter> characterForm =
                new Form<NakazaCharacter>("character",new CompoundPropertyModel<NakazaCharacter>(user.getCharacter()))
        {
            @Override
            protected void onSubmit() {
                userService.saveOrUpdate(user);
            }
        };

        characterForm.add(new FeedbackTextField<String>("name", characterForm).setRequired(true));
        characterForm.add(new FeedbackTextField<Integer>("age", characterForm).setRequired(true));

        characterForm.add(new FeedbackSelectPanel("group", characterForm));
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
