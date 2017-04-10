package org.masch.exercise.planet.orbit.dto;

public class CoordinatePoint {

    private double x;
    private double y;

    public static CoordinatePoint create(double x, double y) {
        return new CoordinatePoint(x, y);
    }

    private CoordinatePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
