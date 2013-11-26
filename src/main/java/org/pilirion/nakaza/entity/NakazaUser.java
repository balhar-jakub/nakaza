package org.pilirion.nakaza.entity;

import org.pilirion.nakaza.api.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jakub Balhar
 * Date: 31.10.13
 * Time: 16:30
 */
@javax.persistence.Table(name = "nakaza_user", schema = "public", catalog = "")
@Entity
public class NakazaUser implements Identifiable<Integer>, Serializable {
    private int id;

    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_key_gen")
    @SequenceGenerator(name = "id_key_gen", sequenceName = "nakaza_user_ids", allocationSize = 1)
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

    private Date dateOfBirth;

    @javax.persistence.Column(name = "date_of_birth", nullable = false, insertable = true, updatable = true, length = 29, precision = 6)
    @Basic
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    private Integer role;

    @javax.persistence.Column(name = "role", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    private NakazaCharacter character;

    @Embedded
    public NakazaCharacter getCharacter() {
        return character;
    }

    public void setCharacter(NakazaCharacter character) {
        this.character = character;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NakazaUser that = (NakazaUser) o;

        if (id != that.id) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
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
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
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

    public static NakazaUser getEmptyUser() {
        NakazaUser emptyUser = new NakazaUser();
        emptyUser.setId_story(new ArrayList<NakazaStory>());
        emptyUser.setCharacter(new NakazaCharacter());
        return emptyUser;
    }
}
