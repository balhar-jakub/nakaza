package org.pilirion.nakaza.entity;

import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:30
 */
@javax.persistence.Table(name = "nakaza_user", schema = "public", catalog = "")
@Entity
public class NakazaUser implements Identifiable<Integer> {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String email;

    @javax.persistence.Column(name = "email", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    private Timestamp dateOfBrith;

    @javax.persistence.Column(name = "date_of_brith", nullable = false, insertable = true, updatable = true, length = 29, precision = 6)
    @Basic
    public Timestamp getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(Timestamp dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
    }

    private String password;

    @javax.persistence.Column(name = "password", nullable = false, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String image;

    @javax.persistence.Column(name = "image", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NakazaUser that = (NakazaUser) o;

        if (id != that.id) return false;
        if (dateOfBrith != null ? !dateOfBrith.equals(that.dateOfBrith) : that.dateOfBrith != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateOfBrith != null ? dateOfBrith.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    private List<NakazaStory> id_story;

    @javax.persistence.JoinTable(name = "nakaza_user_has_story", catalog = "", schema = "public", joinColumns = @javax.persistence.JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "id_story", referencedColumnName = "id", nullable = false))
    @ManyToMany
    public List<NakazaStory> getId_story() {
        return id_story;
    }

    public void setId_story(List<NakazaStory> id_story) {
        this.id_story = id_story;
    }
}
