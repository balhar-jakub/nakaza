package org.pilirion.nakaza.components.page.user;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.behavior.AjaxFeedbackUpdatingBehavior;
import org.pilirion.nakaza.components.Menu;
import org.pilirion.nakaza.components.form.*;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.story.StoryList;
import org.pilirion.nakaza.components.panel.layout.ButtonLike;
import org.pilirion.nakaza.components.panel.layout.LeftMenus;
import org.pilirion.nakaza.components.panel.layout.NakazaSignInPanel;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.UserService;
import org.pilirion.nakaza.utils.FileUtils;
import org.pilirion.nakaza.utils.Pwd;
import org.pilirion.nakaza.utils.RandomString;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Registration extends BasePage {
    @SpringBean
    UserService userService;

    public Registration(){
        init();
    }

    private void init(){
        add(new NakazaSignInPanel("signInPanel"));

        List<ButtonLike> lower = new ArrayList<ButtonLike>();
        add(new LeftMenus("leftMenus", Menu.getMainButtons(), lower));

        NakazaUser logged = userService.getLoggedUser();
        boolean isPwdRequired = false;
        String header = "Registrace";
        if(logged == null) {
            logged = NakazaUser.getEmptyUser();
            isPwdRequired = true;
        } else {
            header = "Editace";
        }
        add(new Label("registrationHeader", header));

        Form<NakazaUser> registration = new Form<NakazaUser>("registration",
                new CompoundPropertyModel<NakazaUser>(logged)){
            @Override
            protected void onSubmit() {
                super.onSubmit();

                NakazaUser user = getModelObject();
                user.setRole((int)NakazaRoles.USER.getRole());
                FileUploadField image = (FileUploadField)get("image");
                FileUpload uploadedImage = image.getFileUpload();
                if(uploadedImage != null) {
                    user.setImage(FileUtils.saveImageFileAndReturnPath(uploadedImage, user.getEmail() +
                            new RandomString(5).nextString(), 120, 120));
                }
                user.setPassword(Pwd.getSHA(user.getPassword()));
                user.setRemainingPoints(20);
                if(userService.saveOrUpdate(user)){
                    throw new RestartResponseException(HomePage.class);
                }
            }
        };

        FeedbackTextField name = new FeedbackTextField("name", registration);
        name.setRequired(true);
        registration.add(name);

        FeedbackPasswordField password = new FeedbackPasswordField("password", registration);
        password.setRequired(isPwdRequired);
        registration.add(password);

        FeedbackPasswordField passwordAgain = new FeedbackPasswordField("passwordAgain", new Model<String>(), registration);
        passwordAgain.setRequired(isPwdRequired);
        registration.add(passwordAgain);

        FeedbackEmailTextField email = new FeedbackEmailTextField("email", registration);
        email.setRequired(true);
        email.setLabel(Model.of("Email"));
        registration.add(email);

        DateTextField dateOfBirth = new FeedbackDateTextField("dateOfBirth", "dd.mm.yyyy", registration);
        dateOfBirth.setRequired(true);
        registration.add(dateOfBirth);

        FileUploadField image = new FeedbackFileUploadField("image", registration);
        registration.add(image);

        Button submit = new Button("submit");
        registration.add(submit);

        registration.add(new EqualPasswordInputValidator(password, passwordAgain));
        add(registration);
    }
}
