package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaParticipant;
import org.pilirion.nakaza.entity.NakazaUser;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:56
 */
public interface ParticipantService extends GenericService<NakazaParticipant> {
    boolean participate(NakazaUser loggedUser, NakazaParticipant participant);
}
