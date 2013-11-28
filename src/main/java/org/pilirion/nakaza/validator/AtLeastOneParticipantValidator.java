package org.pilirion.nakaza.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.pilirion.nakaza.entity.NakazaParticipant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AtLeastOneParticipantValidator implements IValidator<ArrayList<NakazaParticipant>> {
    @Override
    public void validate(IValidatable<ArrayList<NakazaParticipant>> validatable) {
        List<NakazaParticipant> participants = validatable.getValue();

        if(participants.isEmpty()){
            error(validatable, "one-participant-required");
        }
    }

    private void error(IValidatable validatable, String message) {
        ValidationError error = new ValidationError();
        error.addKey(message);
        validatable.error(error);
    }
}
