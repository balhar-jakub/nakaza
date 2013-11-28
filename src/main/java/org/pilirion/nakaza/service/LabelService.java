package org.pilirion.nakaza.service;

import org.pilirion.nakaza.entity.NakazaLabel;

import java.io.Serializable;
import java.util.List;

public interface LabelService extends GenericService<NakazaLabel> {
    List<NakazaLabel> getByAutoCompletable(String labelName);
}
