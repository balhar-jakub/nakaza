package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaStory;
import org.pilirion.nakaza.entity.NakazaUser;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public interface StoryService extends GenericService<NakazaStory> {
    List<NakazaStory> getLastAdded(int maxStories);

    List<NakazaStory> getAllApproved(int groupId);

    NakazaStory getDetailOfStory(int id);

    boolean hasRights(NakazaUser loggedUser, NakazaStory story);

    boolean participates(NakazaUser loggedUser, NakazaStory story);

    public List<NakazaStory> getApproved();

    List<NakazaStory> getLastAddedApproved(int max_stories);
}
