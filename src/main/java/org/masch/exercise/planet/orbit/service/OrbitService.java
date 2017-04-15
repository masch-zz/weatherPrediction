package org.masch.exercise.planet.orbit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.domain.dto.CoordinatePoint;

public class OrbitService {

    public static OrbitService create() {
        return  new OrbitService();
    }

    private OrbitService() {
    }

    public CoordinatePoint calculateTransferred(double angularVelocityDegree, double radius) {

        return CoordinatePoint.create(Math.cos(Math.toRadians(angularVelocityDegree)) * radius,
                                      Math.sin(Math.toRadians(angularVelocityDegree)) * radius);
    }

    public List<CoordinatePoint> calculatePlanetTransferred(List<Planet> planets) {

        return planets.stream()
                .map(planet ->
                calculateTransferred(planet.incrementDegreeMovement(), planet.getRadius()))
                .collect(Collectors.toList());
    }

}
