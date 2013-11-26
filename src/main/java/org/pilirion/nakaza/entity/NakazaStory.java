package org.pilirion.nakaza.entity;

import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@javax.persistence.Table(name = "nakaza_story", schema = "public", catalog = "")
@Entity
public class NakazaStory implements Identifiable<Integer>, Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_key_gen")
    @SequenceGenerator(name = "id_key_gen", sequenceName = "nakaza_story_ids", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String descriptionPublic;

    @javax.persistence.Column(name = "description_public", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getDescriptionPublic() {
        return descriptionPublic;
    }

    public void setDescriptionPublic(String descriptionPublic) {
        this.descriptionPublic = descriptionPublic;
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

        NakazaStory that = (NakazaStory) o;

        if (id != that.id) return false;
        if (descriptionPrivate != null ? !descriptionPrivate.equals(that.descriptionPrivate) : that.descriptionPrivate != null)
            return false;
        if (descriptionPublic != null ? !descriptionPublic.equals(that.descriptionPublic) : that.descriptionPublic != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (descriptionPublic != null ? descriptionPublic.hashCode() : 0);
        result = 31 * result + (descriptionPrivate != null ? descriptionPrivate.hashCode() : 0);
        return result;
    }

    private List<NakazaLabel> id_label;

    @javax.persistence.JoinTable(name = "nakaza_story_has_label", catalog = "", schema = "public", joinColumns = @javax.persistence.JoinColumn(name = "id_story", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "id_label", referencedColumnName = "id", nullable = false))
    @ManyToMany
    public List<NakazaLabel> getId_label() {
        return id_label;
    }

    public void setId_label(List<NakazaLabel> id_label) {
        this.id_label = id_label;
    }

    private List<NakazaUser> id_user;

    @ManyToMany(mappedBy = "id_story")
    public List<NakazaUser> getId_user() {
        return id_user;
    }

    public void setId_user(List<NakazaUser> id_user) {
        this.id_user = id_user;
    }
}
