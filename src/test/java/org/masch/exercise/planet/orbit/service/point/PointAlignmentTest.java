package org.masch.exercise.planet.orbit.service.point;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import org.masch.exercise.planet.orbit.dto.CoordinatePoint;
import org.masch.exercise.planet.orbit.service.PointService;
import org.masch.exercise.planet.orbit.dto.PointsAlignedResult;

public class PointAlignmentTest {

    private PointService pointService = PointService.create();

    @Test
    public void alignmentsPointsTest() {

        assertTrue(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(6.123233995736766E-17d, -1),
                CoordinatePoint.create(3.061616997868383E-16, 5),
                CoordinatePoint.create(6.123233995736766E-16, -10)))));

        assertTrue(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2.1d, 2),
                CoordinatePoint.create(2, 2),
                CoordinatePoint.create(2, 2)))));

        assertTrue(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2, 1),
                CoordinatePoint.create(2, 2),
                CoordinatePoint.create(2, 3)))));

        assertTrue(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(1, 1),
                CoordinatePoint.create(2, 2),
                CoordinatePoint.create(3, 3)))));

        assertTrue(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-3, 2),
                CoordinatePoint.create(1, -3),
                CoordinatePoint.create(3, -5.5d)))));

        assertTrue(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-1, 4),
                CoordinatePoint.create(2, 5),
                CoordinatePoint.create(1, 14d/3d)))));

    }

    @Test
    public void notAlignmentsPointsTest() {

        assertFalse(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-2, 1),
                CoordinatePoint.create(6, -1),
                CoordinatePoint.create(4, 6)))));

        assertFalse(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(-1, 4),
                CoordinatePoint.create(2, 5),
                CoordinatePoint.create(1, 15d/3d)))));

        assertFalse(pointService.checkPointsAlignment(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2.1d, 1),
                CoordinatePoint.create(2, 2),
                CoordinatePoint.create(2, 6)))
        ));

    }

    @Test
    public void alignmentsPointsWithCenterPointTest() {
        assertPointsAlignmentResult(true, true,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(-3, -3)))));

        assertPointsAlignmentResult(true, true,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(0, 1),
                        CoordinatePoint.create(0, 2),
                        CoordinatePoint.create(0, -3)))));

        assertPointsAlignmentResult(true, true,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 0),
                        CoordinatePoint.create(5, 0),
                        CoordinatePoint.create(-100, 0)))));

    }

    @Test
    public void alignmentsPointsWithNOCenterPointTest() {

        assertPointsAlignmentResult(true, false,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(2.1d, 2),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(2, 2)))));

        assertPointsAlignmentResult(true, false,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(2, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(2, 3)))));

        assertPointsAlignmentResult(true, false,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-3, 2),
                        CoordinatePoint.create(1, -3),
                        CoordinatePoint.create(3, -5.5d)))));

        assertPointsAlignmentResult(true, false,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-1, 4),
                        CoordinatePoint.create(2, 5),
                        CoordinatePoint.create(1, 14d/3d)))));

    }

    @Test
    public void notAlignmentsPointsWithNOCenterPointTest() {

        assertPointsAlignmentResult(false, false,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-1, 4),
                        CoordinatePoint.create(2, 5),
                        CoordinatePoint.create(1, 15d/3d)))));

        assertPointsAlignmentResult(false, false,
                pointService.checkPointsAlignmentWithCenter(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(2.1d, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(2, 6)))));

    }

    private void assertPointsAlignmentResult(boolean pointsAlignmentsExpected, boolean pointsAlignmentsWithCenterExpected, PointsAlignedResult pointsAlignedResult) {
        assertEquals(pointsAlignmentsExpected, pointsAlignedResult.isPointsAlignments());
        assertEquals(pointsAlignmentsWithCenterExpected, pointsAlignedResult.isPointsAlignmentsWithCenter());
    }

}
