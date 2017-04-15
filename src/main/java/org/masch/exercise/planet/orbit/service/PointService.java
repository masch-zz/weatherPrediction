package org.masch.exercise.planet.orbit.service;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;

import org.masch.exercise.planet.orbit.domain.dto.CoordinatePoint;
import org.masch.exercise.planet.orbit.domain.dto.PointsAlignedResult;

public class PointService {

    public final static double deltaCalculation = 0.01;
    private final CoordinatePoint centerPointer = CoordinatePoint.create(0, 0);

    public static PointService create() {
        return new PointService();
    }

    private PointService() {
    }

    public boolean checkPointsAlignment(List<CoordinatePoint> points) {

        return validateAndCheckPointsAlignment(points);
    }

    public PointsAlignedResult checkPointsAlignmentWithCenter(List<CoordinatePoint> points) {

        boolean pointsAlignments = validateAndCheckPointsAlignment(points);

        // We need 2 indifferent point (lets take the first and the second) plus
        // center to evaluate if they are alignments,
        // no need to check all the points of list
        List<CoordinatePoint> pointsToCompareWithCenter = new ArrayList<>(Arrays.asList(
                points.get(0),
                points.get(1),
                centerPointer));

        return PointsAlignedResult.create(pointsAlignments, isEqualsSlope(pointsToCompareWithCenter));
    }

    private boolean validateAndCheckPointsAlignment(List<CoordinatePoint> points) {
        return validateInputForCheckPointsAlignments(points) && isEqualsSlope(points);
    }

    private boolean validateInputForCheckPointsAlignments(List<CoordinatePoint> points) {

        // We need at least 3 points to determinate if they are alignments
        return (points != null && !points.isEmpty() && points.size() >= 3);

    }

    private boolean validateInputForCheckPointsTriangle(List<CoordinatePoint> points) {

        // We need only 3 points to determinate if they shape a triangle
        return (points != null && !points.isEmpty() && points.size() == 3);

    }

    private boolean checkAlignmentWithSameCoordinateValue(List<CoordinatePoint> points) {
        int countPoints = points.size();
        CoordinatePoint firstPoint = points.get(0);

        // Check if the X values of the coordinates are equals
        return ((points.parallelStream()
                .filter(point -> doubleIsEqual(point.getX(), firstPoint.getX()))
                .count() == countPoints)
                // Check if the Y values of the coordinates are equals
                || (points.parallelStream()
                .filter(point -> doubleIsEqual(point.getY(), firstPoint.getY()))
                .count() == countPoints));
    }

    private boolean isEqualsSlope(List<CoordinatePoint> points) {

        if (checkAlignmentWithSameCoordinateValue(points))
            return true;

        Iterator<CoordinatePoint> iterator = points.iterator();
        CoordinatePoint sourceCompare = iterator.next();
        CoordinatePoint nextCompare = iterator.next();

        boolean equalsSlope = true;
        double slopeSource, slopeCompare = calculateSlope(sourceCompare, nextCompare);

        while (iterator.hasNext() && equalsSlope) {
            nextCompare = iterator.next();

            slopeSource = calculateSlope(sourceCompare, nextCompare);
            equalsSlope = doubleIsEqual(slopeSource, slopeCompare);

            sourceCompare = nextCompare;
        }
        return equalsSlope;
    }

    private double calculateSlope(CoordinatePoint coordinatePoint1, CoordinatePoint coordinatePoint2) {
        return (coordinatePoint1.getY() - coordinatePoint2.getY()) /
               (coordinatePoint1.getX() - coordinatePoint2.getX());

    }

    boolean isCenterPointInTriangle(List<CoordinatePoint> points) {
        return isPointInTriangle(points, this.centerPointer);
    }

    public boolean isPointInTriangle(List<CoordinatePoint> points, CoordinatePoint pointToCheck) {

        if (!validateInputForCheckPointsTriangle(points))
            return false;

        boolean signal1 = (calculateTriangleOrientation(pointToCheck, points.get(0), points.get(1)) < 0);
        boolean signal2 = (calculateTriangleOrientation(pointToCheck, points.get(1), points.get(2)) < 0);
        boolean signal3 = (calculateTriangleOrientation(pointToCheck, points.get(2), points.get(0)) < 0);

        return (signal1 == signal2) && (signal2 == signal3);
    }

    private double calculateTriangleOrientation(CoordinatePoint coordinatePoint1, CoordinatePoint coordinatePoint2, CoordinatePoint coordinatePoint3) {

        return ((coordinatePoint1.getX() - coordinatePoint3.getX()) *
                (coordinatePoint2.getY() - coordinatePoint3.getY()))
                -
                ((coordinatePoint2.getX() - coordinatePoint3.getX()) *
                 (coordinatePoint1.getY() - coordinatePoint3.getY()));
    }

    public double calculatePerimeter(List<CoordinatePoint> points) {

        if (!validateInputForCheckPointsTriangle(points))
            return 0;

        return calculateDistance(points.get(1), points.get(0)) +
               calculateDistance(points.get(1), points.get(2)) +
               calculateDistance(points.get(2), points.get(0));
    }

    private double calculateDistance(CoordinatePoint sourcePoint, CoordinatePoint finalPoint) {
        return Math.sqrt(Math.pow(sourcePoint.getX() - finalPoint.getX(), 2) +
                         Math.pow(sourcePoint.getY() - finalPoint.getY(), 2));
    }

    private boolean doubleIsEqual(double d1, double d2) {
        return  doubleIsEqual(d1, d2, deltaCalculation);
    }

    private boolean doubleIsEqual(double d1, double d2, double delta) {
        return Double.compare(d1, d2) == 0 || (Math.abs(d1 - d2) <= delta);

    }

}
