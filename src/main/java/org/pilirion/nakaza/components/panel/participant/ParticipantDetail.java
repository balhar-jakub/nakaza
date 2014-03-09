package org.pilirion.nakaza.components.panel.participant;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.form.BookmarkableLinkWithLabel;
import org.pilirion.nakaza.components.form.HidingLabel;
import org.pilirion.nakaza.components.page.character.CharacterDetail;
import org.pilirion.nakaza.components.panel.LabelsPanel;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.ParticipantService;
import org.pilirion.nakaza.service.UserService;

/**
 *
 */
public class ParticipantDetail extends Panel {
    @SpringBean
    UserService userService;
    @SpringBean
    ParticipantService participantService;
    private boolean isVisible;

    public ParticipantDetail(String id, NakazaParticipant participant, boolean isVisible) {
        super(id);
        this.isVisible = isVisible;

        final boolean isAdmin = userService.getLoggedUser() != null && userService.getLoggedUser().getRole() >= 2;
        final boolean isParticipant = participantService.participate(userService.getLoggedUser(), participant);
        final boolean isPrivateVisible = isParticipant || isAdmin;

        add(new Label("name", Model.of(participant.getName())));
        add(new Label("group", Model.of(participant.getGroupText())));
        add(new Label("descriptionPublic", Model.of(participant.getDescriptionPublic())).setEscapeModelStrings(false));
        add(new HidingLabel("descriptionPrivate",
                Model.of(participant.getDescriptionPrivate()), isPrivateVisible).setEscapeModelStrings(false));

        String linkText = "";
        PageParameters params = new PageParameters();
        if(participant.getUser() != null) {
            linkText = participant.getUser().getName();
            params.set("id", participant.getUser().getId());
        }
        add(new BookmarkableLinkWithLabel("personDetail", CharacterDetail.class, Model.of(linkText), Model.of(params)){
            @Override
            protected void onConfigure() {
                setVisibilityAllowed(isAdmin);
            }
        });

        add(new LabelsPanel("labelsPanel", participant.getLabels()));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(isVisible);
    }
}
