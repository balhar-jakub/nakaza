package org.pilirion.nakaza.service.impl;

import org.apache.wicket.RestartResponseException;
import org.pilirion.nakaza.components.page.story.AddStory;
import org.pilirion.nakaza.dao.StoryDAO;
import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;
import org.pilirion.nakaza.security.NakazaRoles;
import org.pilirion.nakaza.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Implementation of potential business logic focused on Story.
 */
@Repository
public class StoryServiceImpl implements StoryService{
    @Autowired
    StoryDAO storyDAO;

    @Override
    public NakazaStory getById(Serializable id) {
        return storyDAO.findById(id);
    }

    @Override
    public boolean saveOrUpdate(NakazaStory story) {
        if(story.getAccepted() == null){
            story.setAccepted(false);
        }
        return storyDAO.saveOrUpdate(story);
    }

    @Override
    public List<NakazaStory> getAll() {
        return storyDAO.findAll();
    }

    @Override
    public List<NakazaStory> getApproved() {
        return storyDAO.getApproved();
    }

    @Override
    public List<NakazaStory> getLastAddedApproved(int max_stories) {
        return storyDAO.getLastAddedApproved(max_stories);
    }

    @Override
    public List<NakazaStory> getAllUnauthorizedFirst() {
        return storyDAO.getAllUnauthorizedFirst();
    }

    @Override
    public void delete(NakazaStory story) {
        storyDAO.delete(story);
    }

    @Override
    public List<NakazaStory> getLastAdded(int maxStories) {
        return storyDAO.getLastAdded(maxStories);
    }

    @Override
    public List<NakazaStory> getAllApproved(int groupId) {
        return storyDAO.getAllApproved(groupId);
    }

    @Override
    public NakazaStory getDetailOfStory(int id) {
        NakazaStory story = getById(id);
        if(story == null) {
            throw new RestartResponseException(AddStory.class);
        }
        return story;
    }

    @Override
    public boolean hasRights(NakazaUser loggedUser, NakazaStory story) {
        if(loggedUser == null) {
            return false;
        }
        if(loggedUser.getRole() >= NakazaRoles.EDITOR.getRole()) {
            return true;
        }
        if(story.getCreatedBy().getId() == loggedUser.getId()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean participates(NakazaUser loggedUser, NakazaStory story) {
        if(loggedUser != null) {
            if(loggedUser.getStories().contains(story)){
                return true;
            }
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<NakazaStory> getUnique(NakazaStory validatableEntity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<NakazaStory> getFirstChoices(String s, int auto_complete_choices) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
