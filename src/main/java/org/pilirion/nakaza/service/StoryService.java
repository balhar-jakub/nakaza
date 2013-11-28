package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaStory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:57
 */
public interface StoryService extends GenericService<NakazaStory> {
    List<NakazaStory> getLastAdded(int maxStories);
}
