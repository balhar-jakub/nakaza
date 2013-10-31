package org.pilirion.nakaza.entity;

import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:30
 */
@javax.persistence.Table(name = "nakaza_participant", schema = "public", catalog = "")
@Entity
public class NakazaParticipant implements Identifiable<Integer> {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String group;

    @javax.persistence.Column(name = "group", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String descriptionPrivate;

    @javax.persistence.Column(name = "description_private", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getDescriptionPrivate() {
        return descriptionPrivate;
    }

    public void setDescriptionPrivate(String descriptionPrivate) {
        this.descriptionPrivate = descriptionPrivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NakazaParticipant that = (NakazaParticipant) o;

        if (id != that.id) return false;
        if (descriptionPrivate != null ? !descriptionPrivate.equals(that.descriptionPrivate) : that.descriptionPrivate != null)
            return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (descriptionPrivate != null ? descriptionPrivate.hashCode() : 0);
        return result;
    }

    private List<NakazaLabel> id_label;

    @javax.persistence.JoinTable(name = "nakaza_participant_has_label", catalog = "", schema = "public", joinColumns = @javax.persistence.JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "id_label", referencedColumnName = "id", nullable = false))
    @ManyToMany
    public List<NakazaLabel> getId_label() {
        return id_label;
    }

    public void setId_label(List<NakazaLabel> id_label) {
        this.id_label = id_label;
    }
}
