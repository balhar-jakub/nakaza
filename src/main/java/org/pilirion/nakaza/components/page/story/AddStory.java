package org.pilirion.nakaza.components.page.story;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.pilirion.nakaza.components.page.BasePage;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 4.11.13
 * Time: 7:48
 */
public class AddStory extends BasePage {
    public AddStory(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forUrl("jquery-ui.min.js"));
        super.renderHead(response);
    }
}
