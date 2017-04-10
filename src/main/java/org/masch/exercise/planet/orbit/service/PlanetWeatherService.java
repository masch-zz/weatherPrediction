package org.masch.exercise.planet.orbit.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.masch.exercise.planet.orbit.dto.Planet;
import org.masch.exercise.planet.orbit.dto.CoordinatePoint;
import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.dto.PointsAlignedResult;

public class PlanetWeatherService {

    private final OrbitService orbitService;
    private final PointService pointService;

    public static PlanetWeatherService create(OrbitService orbitService, PointService pointService) {
        return new PlanetWeatherService(orbitService, pointService);
    }

    private PlanetWeatherService(OrbitService orbitService, PointService pointService) {
        this.orbitService = orbitService;
        this.pointService = pointService;
    }

    public List<WeatherTypeEnum> predictWeather(List<Planet> planets, int amountMovements) {

        List<WeatherTypeEnum> result = new ArrayList<>(amountMovements);

        IntStream.rangeClosed(1, amountMovements).forEach( x -> {
                    result.add(getWeather(orbitService.calculatePlanetTransferred(planets)));
                    });

        return result;
    }

    public WeatherTypeEnum getWeather(List<CoordinatePoint> points) {

        WeatherTypeEnum result;
        PointsAlignedResult pointsAlignedResult = pointService.checkPointsAlignmentWithCenter(points);

        // TODO: Refactor for more states
        if (pointsAlignedResult.isPointsAlignments()) {
            if (pointsAlignedResult.isPointsAlignmentsWithCenter()) {
                result = WeatherTypeEnum.DROUGHT;
            } else {
                result = WeatherTypeEnum.OPTIMAL;
            }
        } else {
            if (pointService.isCenterPointInTriangle(points)) {
                result = WeatherTypeEnum.RAIN;
            } else {
                result = WeatherTypeEnum.NEUTRAL;
            }
        }

        return result;
    }

}
