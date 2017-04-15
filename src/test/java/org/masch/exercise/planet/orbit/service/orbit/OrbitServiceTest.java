package org.masch.exercise.planet.orbit.service.orbit;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.service.OrbitService;
import org.masch.exercise.planet.orbit.service.PointService;
import org.masch.exercise.planet.orbit.domain.dto.CoordinatePoint;

public class OrbitServiceTest {

    private OrbitService orbitService = OrbitService.create();

    @Test
    public void calculateTransferredTest() {

        double radius = 500;
        double angularVelocityDegree = 1;

        CoordinatePoint coordinatePoint = orbitService.calculateTransferred(angularVelocityDegree, radius);
        assertCoordinatePoint(499.923847578,8.726203219,  coordinatePoint);

        radius = 1000;
        angularVelocityDegree = 5;
        coordinatePoint = orbitService.calculateTransferred(angularVelocityDegree, radius);
        assertCoordinatePoint(996.194698092,87.155742748,  coordinatePoint);

        radius = 2000;
        angularVelocityDegree = 3;
        coordinatePoint = orbitService.calculateTransferred(angularVelocityDegree, radius);
        assertCoordinatePoint(1997.259069509,104.671912486,  coordinatePoint);

    }

    @Test
    public void planetsRotationClockWiseModeTest() {

        int amountMovements = 4;
        List<CoordinatePoint> planetCoordinatePoints = new ArrayList<>();
        List<Planet> planets = new ArrayList<>(Collections.singletonList(Planet.create("Vulcano", 500, true, 1)));
        IntStream.rangeClosed(1, amountMovements).forEach(x ->
                planetCoordinatePoints.addAll(orbitService.calculatePlanetTransferred(planets)));

        assertNotNull(planetCoordinatePoints);
        assertEquals(amountMovements, planetCoordinatePoints.size());
        assertCoordinatePoint(500, 0, planetCoordinatePoints.get(0));
        assertCoordinatePoint(499.923847578, -8.7262032191, planetCoordinatePoints.get(1));
        assertCoordinatePoint(499.695413511, -17.449748351, planetCoordinatePoints.get(2));
        assertCoordinatePoint(499.314767377, -26.167978121, planetCoordinatePoints.get(3));


        amountMovements = 5;
        planetCoordinatePoints.clear();
        List<Planet> planets2 = new ArrayList<>(Collections.singletonList(Planet.create("Earth", 500, true, 90)));
        IntStream.rangeClosed(1, amountMovements).forEach(x ->
                planetCoordinatePoints.addAll(orbitService.calculatePlanetTransferred(planets2)));
        assertNotNull(planetCoordinatePoints);
        assertEquals(amountMovements, planetCoordinatePoints.size());
        assertCoordinatePoint(500, 0, planetCoordinatePoints.get(0));
        assertCoordinatePoint(0, -500, planetCoordinatePoints.get(1));
        assertCoordinatePoint(-500, 0, planetCoordinatePoints.get(2));
        assertCoordinatePoint(0, 500, planetCoordinatePoints.get(3));
        assertCoordinatePoint(500, 0, planetCoordinatePoints.get(4));

    }

    @Test
    public void planetsRotationNOTClockWiseModeTest() {

        int amountMovements = 4;
        List<CoordinatePoint> planetCoordinatePoints = new ArrayList<>();
        List<Planet> planets = new ArrayList<>(Collections.singletonList(Planet.create("Ferengi", 500, false, 1)));
        IntStream.rangeClosed(1, amountMovements).forEach(x ->
                              planetCoordinatePoints.addAll(orbitService.calculatePlanetTransferred(planets)));

        assertNotNull(planetCoordinatePoints);
        assertEquals(amountMovements, planetCoordinatePoints.size());
        assertCoordinatePoint(500, 0, planetCoordinatePoints.get(0));
        assertCoordinatePoint(499.923847578, 8.7262032191, planetCoordinatePoints.get(1));
        assertCoordinatePoint(499.695413511, 17.449748351, planetCoordinatePoints.get(2));
        assertCoordinatePoint(499.314767377, 26.167978121, planetCoordinatePoints.get(3));


        amountMovements = 5;
        planetCoordinatePoints.clear();
        List<Planet> planets2 = new ArrayList<>(Collections.singletonList(Planet.create("Earth", 500, false, 90)));
        IntStream.rangeClosed(1, amountMovements).forEach(x ->
                planetCoordinatePoints.addAll(orbitService.calculatePlanetTransferred(planets2)));
        assertNotNull(planetCoordinatePoints);
        assertEquals(amountMovements, planetCoordinatePoints.size());
        assertCoordinatePoint(500, 0, planetCoordinatePoints.get(0));
        assertCoordinatePoint(0, 500, planetCoordinatePoints.get(1));
        assertCoordinatePoint(-500, 0, planetCoordinatePoints.get(2));
        assertCoordinatePoint(0, -500, planetCoordinatePoints.get(3));
        assertCoordinatePoint(500, 0, planetCoordinatePoints.get(4));
    }


    private void assertCoordinatePoint(double xExpected, double yExpected, CoordinatePoint coordinatePoint) {
        assertNotNull(coordinatePoint);
        assertEquals(xExpected, coordinatePoint.getX(), PointService.deltaCalculation);
        assertEquals(yExpected, coordinatePoint.getY(), PointService.deltaCalculation);

    }

}
