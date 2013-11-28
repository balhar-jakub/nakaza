package org.pilirion.nakaza;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.pilirion.nakaza.components.page.character.CharacterList;
import org.pilirion.nakaza.components.page.character.CreateCharacter;
import org.pilirion.nakaza.components.page.statics.AboutGame;
import org.pilirion.nakaza.components.page.statics.AboutWorld;
import org.pilirion.nakaza.components.page.statics.HomePage;
import org.pilirion.nakaza.components.page.statics.NoRights;
import org.pilirion.nakaza.components.page.story.AddStory;
import org.pilirion.nakaza.components.page.story.AdministerStories;
import org.pilirion.nakaza.components.page.story.CreateStory;
import org.pilirion.nakaza.components.page.story.StoryDetail;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.page.user.Registration;
import org.pilirion.nakaza.converter.LabelConverter;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.security.NakazaAuthenticatedWebSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 4.9.13
 * Time: 18:02
 */
@Component(value = "wicketApplication")
public class Nakaza extends AuthenticatedWebApplication implements ApplicationContextAware {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private ApplicationContext ctx;

    @Autowired
    private LabelConverter labelConverter;

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return NakazaAuthenticatedWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return Login.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx, true));
        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getMarkupSettings().setStripWicketTags(true);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);

        mountPages();
    }

    private void mountPages() {
        mountPage("/about-game", AboutGame.class);
        mountPage("/about-world", AboutWorld.class);
        mountPage("/no-rights", NoRights.class);

        mountPage("/list-character", CharacterList.class);
        mountPage("/create-character", CreateCharacter.class);

        mountPage("/add-story-to-character", AddStory.class);
        mountPage("/create-story", CreateStory.class);
        mountPage("/administer-stories", AdministerStories.class);
        mountPage("/story-detail", StoryDetail.class);

        mountPage("/login", Login.class);
        mountPage("/registration", Registration.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public static String getBaseContext(){
        return "upload/";
    }

    protected IConverterLocator newConverterLocator() {
        ConverterLocator locator = (ConverterLocator) super.newConverterLocator();

        locator.set(NakazaLabel.class, labelConverter);

        return locator;

    }
}
