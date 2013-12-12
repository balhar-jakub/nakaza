package org.pilirion.nakaza.dao;

import org.pilirion.nakaza.api.GenericHibernateDAO;
import org.pilirion.nakaza.entity.NakazaParticipant;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Implementation of GenericHibernateDAO for Participant.
 */
@Repository
public class ParticipantDAO extends GenericHibernateDAO<NakazaParticipant, Serializable> {
}
