package org.pilirion.nakaza.components.panel.participant;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.form.HidingLabel;
import org.pilirion.nakaza.components.panel.LabelsPanel;
import org.pilirion.nakaza.entity.NakazaParticipant;
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

        final boolean isPrivateVisible =
                participantService.participate(userService.getLoggedUser(), participant);

        add(new Label("name", Model.of(participant.getName())));
        add(new Label("group", Model.of(participant.getGroupText())));
        add(new Label("descriptionPublic", Model.of(participant.getDescriptionPublic())).setEscapeModelStrings(false));
        add(new HidingLabel("descriptionPrivate",
                Model.of(participant.getDescriptionPrivate()), isPrivateVisible).setEscapeModelStrings(false));

        add(new LabelsPanel("labelsPanel", participant.getLabels()));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();

        setVisibilityAllowed(isVisible);
    }
}
