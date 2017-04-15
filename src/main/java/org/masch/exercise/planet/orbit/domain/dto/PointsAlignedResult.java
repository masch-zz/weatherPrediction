package org.masch.exercise.planet.orbit.domain.dto;

public class PointsAlignedResult {

    private boolean pointsAlignments;
    private boolean pointsAlignmentsWithCenter;

    private PointsAlignedResult(boolean pointsAlignments, boolean pointsAlignmentsWithCenter) {
        this.pointsAlignments = pointsAlignments;
        this.pointsAlignmentsWithCenter = pointsAlignmentsWithCenter;
    }

    public static PointsAlignedResult create(boolean pointsAlignments, boolean pointsAlignmentsWithCenter) {
        return new PointsAlignedResult(pointsAlignments, pointsAlignmentsWithCenter);
    }

    public boolean isPointsAlignments() {
        return pointsAlignments;
    }

    public boolean isPointsAlignmentsWithCenter() {
        return pointsAlignmentsWithCenter;
    }

}
