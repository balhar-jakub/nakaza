package org.pilirion.nakaza.entity;

import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:30
 */
@javax.persistence.Table(name = "nakaza_label", schema = "public", catalog = "")
@Entity
public class NakazaLabel implements Identifiable<Integer>, Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_key_gen")
    @SequenceGenerator(name = "id_key_gen", sequenceName = "nakaza_label_ids", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String description;

    @javax.persistence.Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NakazaLabel that = (NakazaLabel) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    private List<NakazaParticipant> id_participant;

    @ManyToMany(mappedBy = "id_label")
    public List<NakazaParticipant> getId_participant() {
        return id_participant;
    }

    public void setId_participant(List<NakazaParticipant> id_participant) {
        this.id_participant = id_participant;
    }

    private List<NakazaStory> id_story;

    @ManyToMany(mappedBy = "id_label")
    public List<NakazaStory> getId_story() {
        return id_story;
    }

    public void setId_story(List<NakazaStory> id_story) {
        this.id_story = id_story;
    }
}
