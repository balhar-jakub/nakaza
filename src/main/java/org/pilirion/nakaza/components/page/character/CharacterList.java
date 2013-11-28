package org.pilirion.nakaza.components.page.character;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.pilirion.nakaza.components.page.BasePage;
import org.pilirion.nakaza.components.page.user.Login;
import org.pilirion.nakaza.components.panel.character.CreateCharacterPanel;
import org.pilirion.nakaza.components.panel.character.ListCharacters;
import org.pilirion.nakaza.entity.NakazaCharacter;
import org.pilirion.nakaza.service.UserService;

import java.util.List;

/**
 *
 */
public class CharacterList extends BasePage {
    @SpringBean
    UserService characterService;

    public CharacterList(PageParameters params){
        init(params);
    }

    private void init(PageParameters params){
        List<NakazaCharacter> charactersData = characterService.getCharacters();

        add(new ListCharacters("charactersPanel", charactersData));

        add(new BookmarkablePageLink<BasePage>("login", Login.class));

        add(new CreateCharacterPanel("characterCreation"));
    }
}
