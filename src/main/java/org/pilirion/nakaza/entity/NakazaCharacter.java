package org.pilirion.nakaza.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 */
@Embeddable
public class NakazaCharacter implements Serializable {
    private String name;

    @Column(name = "character_name", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer age;

    @Column(name = "character_age", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String group;

    @Column(name = "character_group", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private String description;

    @Column(name = "character_description", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
