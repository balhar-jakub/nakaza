package org.pilirion.nakaza.service.impl;

import org.pilirion.nakaza.dao.ParticipantDAO;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:53
 */
@Repository
public class ParticipantServiceImpl implements ParticipantService{
    @Autowired
    ParticipantDAO participantDAO;

    @Override
    public NakazaParticipant getById(Serializable id) {
        return participantDAO.findById(id);
    }

    @Override
    public boolean saveOrUpdate(NakazaParticipant participant) {
        return participantDAO.saveOrUpdate(participant);
    }

    @Override
    public List<NakazaParticipant> getAll() {
        return participantDAO.findAll();
    }

    @Override
    public void delete(NakazaParticipant participant) {
        participantDAO.delete(participant);
    }

    @Override
    public List<NakazaParticipant> getUnique(NakazaParticipant validatableEntity) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<NakazaParticipant> getFirstChoices(String s, int auto_complete_choices) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
