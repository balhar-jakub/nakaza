package org.pilirion.nakaza.converter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.util.convert.IConverter;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class LabelConverter implements IConverter<NakazaLabel> {
    @Autowired
    private LabelService labelService;

    @Override
    public NakazaLabel convertToObject(String labelName, Locale locale) {
        List<NakazaLabel> foundLabels = labelService.getByAutoCompletable(labelName);
        int amountOfLabels = foundLabels.size();
        if(amountOfLabels == 1) {
            return foundLabels.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String convertToString(NakazaLabel label, Locale locale) {
        String stringRepresentation = label.getAutoCompleteData();
        return stringRepresentation != null ? stringRepresentation : "";
    }
}
