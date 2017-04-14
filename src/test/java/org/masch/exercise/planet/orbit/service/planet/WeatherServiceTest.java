package org.masch.exercise.planet.orbit.service.planet;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.masch.exercise.planet.orbit.dto.CoordinatePoint;
import org.masch.exercise.planet.orbit.dto.WeatherPrediction;
import org.masch.exercise.planet.orbit.service.OrbitService;
import org.masch.exercise.planet.orbit.service.PointService;
import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.service.PlanetWeatherService;

public class WeatherServiceTest {

    private PlanetWeatherService planetWeatherService = PlanetWeatherService.create(
            OrbitService.create(),
            PointService.create());

    @Test
    public void optimalWeatherTest() {

        final WeatherTypeEnum expectedWeather = WeatherTypeEnum.OPTIMAL;

        // Aligned pointers, but not with center
        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2.1d, 2),
                CoordinatePoint.create(2, 2),
                CoordinatePoint.create(2, 2)
        ))));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(2, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(2, 3)
                ))));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-3, 2),
                        CoordinatePoint.create(1, -3),
                        CoordinatePoint.create(3, -5.5d)
                ))));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-1, 4),
                        CoordinatePoint.create(2, 5),
                        CoordinatePoint.create(1, 14d/3d)
                ))));

    }

    @Test
    public void droughtWeatherTest() {

        final WeatherTypeEnum expectedWeather = WeatherTypeEnum.DROUGHT;

        // Aligned pointers with center
        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(3, 3)
                ))));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(-3, -3)
                ))));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(0, 1),
                        CoordinatePoint.create(0, 2),
                        CoordinatePoint.create(0, -3)
                ))));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 0),
                        CoordinatePoint.create(5, 0),
                        CoordinatePoint.create(-100, 0)
                ))));

    }

    @Test
    public void rainWeatherTest() {

        // Triangle pointers with center
        WeatherTypeEnum expectedWeather = WeatherTypeEnum.RAIN;

        assertWeatherPrediction(expectedWeather, 31.04,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-3, 7),
                        CoordinatePoint.create(1, -5),
                        CoordinatePoint.create(5, 4))
                )));

        assertWeatherPrediction(expectedWeather, 32.80,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-6, 1),
                        CoordinatePoint.create(-2, -6),
                        CoordinatePoint.create(3, 7)
                ))));

        assertWeatherPrediction(expectedWeather, 30.50,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(0, -6),
                        CoordinatePoint.create(-7, 1),
                        CoordinatePoint.create(5, 1))
                )));

    }

    @Test
    public void neutralWeatherTest() {

        // Triangle pointers with NO center
        WeatherTypeEnum expectedWeather = WeatherTypeEnum.NEUTRAL;

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-2, 1),
                        CoordinatePoint.create(6, -1),
                        CoordinatePoint.create(4, 6))
                )));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-2, 5),
                        CoordinatePoint.create(6, 6),
                        CoordinatePoint.create(5, -4))
                )));

        assertWeatherPrediction(expectedWeather, 0,
                planetWeatherService.getWeatherPrediction(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-6, 1),
                        CoordinatePoint.create(-1, -2),
                        CoordinatePoint.create(3, 7)
                ))));

    }

    private void assertWeatherPrediction(WeatherTypeEnum expectedWeather, double expectedPerimeter, WeatherPrediction weatherPrediction) {
        assertEquals(expectedWeather, weatherPrediction.getWeatherTypeEnum());
        assertEquals(expectedPerimeter, weatherPrediction.getPerimeter(), PointService.deltaCalculation);

    }
}
