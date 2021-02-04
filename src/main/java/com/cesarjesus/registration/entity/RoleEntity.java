package com.cesarjesus.registration.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

    @Id
    private String id;
    @Column(name = "name")
    private String name;

    public RoleEntity(){}

    public RoleEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
