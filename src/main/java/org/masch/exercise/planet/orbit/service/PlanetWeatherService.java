package org.masch.exercise.planet.orbit.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.domain.dto.CoordinatePoint;
import org.masch.exercise.planet.orbit.domain.dto.WeatherPrediction;
import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.domain.dto.PointsAlignedResult;

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

    public List<WeatherPrediction> predictWeather(List<Planet> planets, int amountMovements) {
        final double[] maxTrianglePerimeter = {0d};
        List<WeatherPrediction> weatherPredictions = new ArrayList<>(amountMovements);

        IntStream.rangeClosed(1, amountMovements).forEach( x -> {
            WeatherPrediction weatherPrediction = getWeatherPrediction(orbitService.calculatePlanetTransferred(planets));
            if (weatherPrediction.getPerimeter() > maxTrianglePerimeter[0])
                maxTrianglePerimeter[0] = weatherPrediction.getPerimeter();

            weatherPredictions.add(weatherPrediction);
        });

        setMaxPeriodWeatherType(maxTrianglePerimeter[0], weatherPredictions, WeatherTypeEnum.MAX_RAIN);

        return weatherPredictions;
    }

    private void setMaxPeriodWeatherType(double maxPerimeterValue, List<WeatherPrediction> result, WeatherTypeEnum weatherType) {
        result.stream()
                .filter(weatherPrediction -> weatherPrediction.getPerimeter() == maxPerimeterValue)
                .forEach(weatherPrediction -> weatherPrediction.setWeatherTypeEnum(weatherType));
    }

    public WeatherPrediction getWeatherPrediction(List<CoordinatePoint> points) {

        double perimeter = 0;
        WeatherTypeEnum weatherType;
        PointsAlignedResult pointsAlignedResult = pointService.checkPointsAlignmentWithCenter(points);

        // TODO: Refactor for more states
        if (pointsAlignedResult.isPointsAlignments()) {
            if (pointsAlignedResult.isPointsAlignmentsWithCenter()) {
                weatherType = WeatherTypeEnum.DROUGHT;
            } else {
                weatherType = WeatherTypeEnum.OPTIMAL;
            }
        } else {
            if (pointService.isCenterPointInTriangle(points)) {
                weatherType = WeatherTypeEnum.RAIN;
                perimeter =  pointService.calculatePerimeter(points);
            } else {
                weatherType = WeatherTypeEnum.NEUTRAL;
            }
        }

        return WeatherPrediction.create(perimeter, weatherType);
    }

}
