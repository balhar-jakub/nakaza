package org.pilirion.nakaza.components.page;

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
    protected void setHeaders(WebResponse response) {
        super.setHeaders(response);
        //Protection against ClickJacking, prevents the page from being rendered in an iframe element
        response.setHeader("X-Frame-Options","deny");
    }
}
