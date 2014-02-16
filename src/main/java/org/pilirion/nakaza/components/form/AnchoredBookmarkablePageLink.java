package org.pilirion.nakaza.components.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 */
public class AnchoredBookmarkablePageLink extends BookmarkablePageLink {

    private static final long serialVersionUID = 1L;

    private IModel stringAnchor;

    public AnchoredBookmarkablePageLink(String id, Class pageClass, IModel
            anchor) {
        super(id, pageClass);
        this.stringAnchor = anchor;
    }

    public AnchoredBookmarkablePageLink(String id, Class pageClass,
                                        PageParameters params, IModel anchor) {
        super(id, pageClass, params);
        this.stringAnchor = anchor;
    }

    @Override
    protected CharSequence appendAnchor(ComponentTag tag, CharSequence url) {
        url = url + "#" + stringAnchor.getObject().toString();
        return url;
    }

}

