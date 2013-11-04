package org.pilirion.nakaza.components.page;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.http.WebResponse;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 4.9.13
 * Time: 18:08
 */
public class BasePage extends WebPage {
    public BasePage(){

    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings()
                .getJQueryReference()));

        //response.render(JavaScriptHeaderItem.forUrl("/files/js/jquery.nivo.slider.js"));
        //response.render(CssHeaderItem.forUrl("/files/css/smoothness/jquery-ui-1.8.24.custom.css"));
        super.renderHead(response);
    }

    @Override
    protected void setHeaders(WebResponse response) {
        super.setHeaders(response);
        //Protection against ClickJacking, prevents the page from being rendered in an iframe element
        response.setHeader("X-Frame-Options","deny");
    }
}
