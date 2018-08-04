package com.expertsoft.core.model.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY;

@Entity
@Immutable
@Cache(usage = READ_ONLY, region = "accounts")
public class Role {

    @Id
    private Long id;

    @NaturalId
    @Basic(optional = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Role of(Long id, String name) {
        var role = new Role();
        role.setId(id);
        role.setName(name);
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var role = (Role) o;
        return Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
