package org.masch.exercise.planet.orbit.domain.dto;

public class Planet {

    private String name;
    private double radius;
    private int signalRotation;
    private boolean clockWiseRotation;
    private double degreeAngularVelocity;
    private double degreeRotationPosition;

    private Planet(String name, double radius, boolean clockWiseRotation, double degreeAngularVelocity) {
        this.name = name;
        this.radius = radius;
        this.degreeRotationPosition = 0d;
        this.clockWiseRotation = clockWiseRotation;
        this.signalRotation = clockWiseRotation ? -1 : 1;
        this.degreeAngularVelocity = degreeAngularVelocity;
    }

    public static Planet create(String name, double radius, boolean clockWiseRotation, double degreeAngularVelocity) {
        return new Planet(name, radius, clockWiseRotation, degreeAngularVelocity);
    }

    public double getRadius() {
        return radius;
    }

    public double incrementDegreeMovement() {
        double currentDegreeRotationPosition = degreeRotationPosition;
        degreeRotationPosition += this.degreeAngularVelocity * this.signalRotation;
        if (Math.abs(degreeRotationPosition) > 360) {
            degreeRotationPosition = 0;
        }
        return currentDegreeRotationPosition;
    }

}
