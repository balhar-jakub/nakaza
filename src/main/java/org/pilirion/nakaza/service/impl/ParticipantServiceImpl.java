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
        return participantDAO.findById(id, false);
    }

    @Override
    public void saveOrUpdate(NakazaParticipant participant) {
        participantDAO.makePersistent(participant);
    }

    @Override
    public List<NakazaParticipant> getAll() {
        return participantDAO.findAll();
    }

    @Override
    public void delete(NakazaParticipant participant) {
        participantDAO.makeTransient(participant);
    }

    @Override
    public List<NakazaParticipant> findByExample(NakazaParticipant participant) {
        return participantDAO.findByExample(participant,new String[]{});
    }
}
