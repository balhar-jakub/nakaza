package org.pilirion.nakaza.entity;

import org.hibernate.annotations.Cascade;
import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
@javax.persistence.Table(name = "nakaza_participant", schema = "public", catalog = "")
@Entity
public class NakazaParticipant implements Identifiable<Integer>, Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_key_gen")
    @SequenceGenerator(name = "id_key_gen", sequenceName = "nakaza_participant_ids", allocationSize = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String group;

    @javax.persistence.Column(
            name = "group_id",
            nullable = true,
            insertable = true,
            updatable = true,
            length = 2147483647,
            precision = 0)
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

    private String descriptionPublic;

    @javax.persistence.Column(name = "description_public", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getDescriptionPublic() {
        return descriptionPublic;
    }

    public void setDescriptionPublic(String descriptionPublic) {
        this.descriptionPublic = descriptionPublic;
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

    private List<NakazaLabel> labels;

    @javax.persistence.JoinTable(name = "nakaza_participant_has_label", catalog = "", schema = "public", joinColumns = @javax.persistence.JoinColumn(name = "id_participant", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "id_label", referencedColumnName = "id", nullable = false))
    @ManyToMany
    public List<NakazaLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<NakazaLabel> labels) {
        this.labels = labels;
    }

    private NakazaStory story;

    @ManyToOne()
    @JoinColumn(
            name = "story",
            referencedColumnName = "id",
            insertable = true,
            updatable = true
    )
    public NakazaStory getStory() {
        return story;
    }

    public void setStory(NakazaStory story) {
        this.story = story;
    }

    private NakazaUser user;

    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(
            name = "id_user",
            referencedColumnName = "id",
            insertable = true,
            updatable = true
    )
    public NakazaUser getUser() {
        return user;
    }

    public void setUser(NakazaUser user) {
        this.user = user;
    }


    public static NakazaParticipant getEmptyParticipant() {
        NakazaParticipant participant = new NakazaParticipant();
        participant.setLabels(new ArrayList<NakazaLabel>());
        return participant;
    }

    @Transient
    public String getGroupText() {
        String groupText;
        if(getGroup().equals("0")) {
            groupText = "Zombie";
        } else if(getGroup().equals("1")) {
            groupText = "Přeživší";
        } else if(getGroup().equals("2")) {
            groupText = "Armáda";
        } else {
            groupText = "";
        }
        return groupText;
    }
}
