package org.pilirion.nakaza.service.impl;

import org.pilirion.nakaza.dao.LabelDAO;
import org.pilirion.nakaza.entity.NakazaLabel;
import org.pilirion.nakaza.service.LabelService;
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
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelDAO labelDAO;

    @Override
    public NakazaLabel getById(Serializable id) {
        return labelDAO.findById(id, false);
    }

    @Override
    public boolean saveOrUpdate(NakazaLabel label) {
        return labelDAO.saveOrUpdate(label);
    }

    @Override
    public List<NakazaLabel> getAll() {
        return labelDAO.findAll();
    }

    @Override
    public void delete(NakazaLabel label) {
        labelDAO.makeTransient(label);
    }

    @Override
    public List<NakazaLabel> findByExample(NakazaLabel label) {
        return labelDAO.findByExample(label, new String[]{});
    }
}
