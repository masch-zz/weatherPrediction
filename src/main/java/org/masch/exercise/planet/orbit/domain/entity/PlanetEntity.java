package org.masch.exercise.planet.orbit.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class PlanetEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public static PlanetEntity create(String name) {
        return new PlanetEntity(null, name);
    }

    public static PlanetEntity create(Long id, String name) {
        return new PlanetEntity(id, name);
    }

    private PlanetEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private PlanetEntity() {
    }
}
