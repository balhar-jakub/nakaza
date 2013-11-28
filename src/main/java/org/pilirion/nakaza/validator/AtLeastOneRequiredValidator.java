package org.pilirion.nakaza.validator;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;
import org.pilirion.nakaza.entity.NakazaLabel;

import java.util.List;

/**
 *
 */
public class AtLeastOneRequiredValidator implements IValidator<List<NakazaLabel>> {
    @Override
    public void validate(IValidatable<List<NakazaLabel>> validatable) {
        List<NakazaLabel> labels = validatable.getValue();
        if(labels.isEmpty()) {
            error(validatable, "one-required-label");
        }
    }

    private void error(IValidatable validatable, String message) {
        ValidationError error = new ValidationError();
        error.addKey(message);
        validatable.error(error);
    }
}
