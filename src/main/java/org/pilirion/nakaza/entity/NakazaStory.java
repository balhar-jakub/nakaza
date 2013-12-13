package org.pilirion.nakaza.entity;

import org.hibernate.annotations.Cascade;
import org.hsqldb.User;
import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    private Integer points;

    @javax.persistence.Column(name = "points", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    private Boolean accepted;

    @javax.persistence.Column(name = "state", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
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

    private NakazaUser createdBy;

    @ManyToOne()
    @JoinColumn(
            name = "created_by",
            referencedColumnName = "id",
            insertable = true,
            updatable = true
    )
    public NakazaUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(NakazaUser createdBy) {
        this.createdBy = createdBy;
    }

    private List<NakazaLabel> labels;

    @javax.persistence.JoinTable(name = "nakaza_story_has_label", catalog = "", schema = "public", joinColumns = @javax.persistence.JoinColumn(name = "id_story", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "id_label", referencedColumnName = "id", nullable = false))
    @ManyToMany
    public List<NakazaLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<NakazaLabel> labels) {
        this.labels = labels;
    }

    private List<NakazaUser> users;

    @ManyToMany(mappedBy = "stories")
    public List<NakazaUser> getUsers() {
        return users;
    }

    public void setUsers(List<NakazaUser> users) {
        this.users = users;
    }

    private List<NakazaParticipant> participants;

    @OneToMany(
            mappedBy = "story",
            fetch = FetchType.EAGER
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public List<NakazaParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<NakazaParticipant> participants) {
        this.participants = participants;
    }

    @Transient
    private String group;

    @Transient
    public void setGroup(String group) {
        this.group = group;
    }

    @Transient
    public String getGroup(){
        return group;
    }

    public static NakazaStory getEmptyStory() {
        NakazaStory story = new NakazaStory();
        story.setLabels(new ArrayList<NakazaLabel>());
        story.setParticipants(new ArrayList<NakazaParticipant>());
        story.setUsers(new ArrayList<NakazaUser>());
        return story;
    }
}
