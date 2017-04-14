package org.masch.exercise.planet.orbit.service.point;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;
import org.masch.exercise.planet.orbit.dto.CoordinatePoint;
import org.masch.exercise.planet.orbit.service.PointService;

public class PointTriangleTest {
    private PointService pointService = PointService.create();

    @Test
    public void isPointInTriangle() {

        assertTrue(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2, 0),
                CoordinatePoint.create(3, 6),
                CoordinatePoint.create(0, 6))),
                CoordinatePoint.create(2, 3)));

        assertTrue(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-2, 3),
                CoordinatePoint.create(3, -1),
                CoordinatePoint.create(5, 6))),
                CoordinatePoint.create(2, 3)));

        assertTrue(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-3, 7),
                CoordinatePoint.create(1, -5),
                CoordinatePoint.create(5, 4))),
                CoordinatePoint.create(0, 0)));

    }

    @Test
    public void isNOTPointInTriangle() {

        assertFalse(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2, 0),
                CoordinatePoint.create(3, 6),
                CoordinatePoint.create(0, 6))),
                CoordinatePoint.create(0, 0)));

        assertFalse(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-2, 3),
                CoordinatePoint.create(3, -1),
                CoordinatePoint.create(5, 6))),
                CoordinatePoint.create(-1, 7)));

        assertFalse(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-3, 7),
                CoordinatePoint.create(1, -5),
                CoordinatePoint.create(5, 4))),
                CoordinatePoint.create(3, -3)));

    }

    @Test
    public void isPointInTriangleInvalidInput() {

        assertFalse(pointService.isPointInTriangle(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2, 0),
                CoordinatePoint.create(3, 6))),
                CoordinatePoint.create(2, 3)));

    }

    @Test
    public void calculatePerimeter() {
        assertEquals(16.70, pointService.calculatePerimeter(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-3, 0),
                CoordinatePoint.create(1, 3),
                CoordinatePoint.create(3, -2)))), PointService.deltaCalculation);
    }

}
