package org.pilirion.nakaza.components.form;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;

public class BookmarkableLinkWithLabel extends Panel {
    private Class linkPage;
    private IModel<String> text;
    private IModel<PageParameters> params;

    public BookmarkableLinkWithLabel(String id,
                                     Class linkPage,
                                     IModel<String> text) {
        this(id, linkPage, text, Model.of(new PageParameters()));
    }

    public BookmarkableLinkWithLabel(String id,
                                     Class linkPage,
                                     IModel<String> text,
                                     IModel<PageParameters> params) {
        super(id);

        this.linkPage = linkPage;
        this.text = text;
        this.params = params;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        BookmarkablePageLink<BasePage> link =
                new BookmarkablePageLink<BasePage>("link", linkPage, params.getObject());
        link.add(new Label("label", text));
        add(link);
    }
}