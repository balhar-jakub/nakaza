package org.pilirion.nakaza.service.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.pilirion.nakaza.dao.StoryDAO;
import org.pilirion.nakaza.entity.NakazaStory;
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
        return storyDAO.saveOrUpdate(story);
    }

    @Override
    public List<NakazaStory> getAll() {
        return storyDAO.findAll();
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
    public List<NakazaStory> getUnique(NakazaStory validatableEntity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<NakazaStory> getFirstChoices(String s, int auto_complete_choices) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
