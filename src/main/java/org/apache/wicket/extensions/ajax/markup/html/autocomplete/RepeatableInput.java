package org.apache.wicket.extensions.ajax.markup.html.autocomplete;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;
import org.pilirion.nakaza.service.GenericService;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public class RepeatableInput<T extends IAutoCompletable> extends AutoCompleteTextField<T> {
    protected GenericService<T> service;

    public RepeatableInput(String id,

                           IModel<T> model,
                           Class<T> type,

                           GenericService<T> service) {
        super(id, model, type, new AbstractAutoCompleteTextRenderer<T>() {
            @Override
            protected String getTextValue(T object) {
                return object.getAutoCompleteData();
            }
        }, new AutoCompleteSettings());

        this.service = service;

        setOutputMarkupId(true);
    }

    @Override
    protected Iterator<T> getChoices(String input) {
        if(Strings.isEmpty(input)){
            return new ArrayList<T>().iterator();
        }
        int AUTO_COMPLETE_CHOICES = 10;
        return service.getFirstChoices(input.toLowerCase(), AUTO_COMPLETE_CHOICES).iterator();
    }
}
