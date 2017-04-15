package org.masch.exercise.planet.orbit.domain.entity;

import javax.persistence.Id;
import javax.persistence.Column;
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

    @Column
    private double radius;

    @Column
    private boolean clockWiseRotation;

    @Column
    private double degreeAngularVelocity;

    public static PlanetEntity create(String name, double radius, boolean clockWiseRotation, double degreeAngularVelocity) {
        return new PlanetEntity(null, name, radius, clockWiseRotation, degreeAngularVelocity);
    }

    public static PlanetEntity create(Long id, String name, double radius, boolean clockWiseRotation, double degreeAngularVelocity) {
        return new PlanetEntity(id, name, radius, clockWiseRotation, degreeAngularVelocity);
    }

    private PlanetEntity(Long id, String name, double radius, boolean clockWiseRotation, double degreeAngularVelocity) {
        this.id = id;
        this.name = name;
        this.radius = radius;
        this.clockWiseRotation = clockWiseRotation;
        this.degreeAngularVelocity = degreeAngularVelocity;
    }

    private PlanetEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isClockWiseRotation() {
        return clockWiseRotation;
    }

    public double getDegreeAngularVelocity() {
        return degreeAngularVelocity;
    }
}
