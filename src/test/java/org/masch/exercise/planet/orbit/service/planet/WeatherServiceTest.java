package org.masch.exercise.planet.orbit.service.planet;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.masch.exercise.planet.orbit.dto.CoordinatePoint;
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

        final WeatherTypeEnum WEATHER_EXPECTED = WeatherTypeEnum.OPTIMAL;

        // Aligned pointers, but not with center
        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                CoordinatePoint.create(2.1d, 2),
                CoordinatePoint.create(2, 2),
                CoordinatePoint.create(2, 2)
        ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(2, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(2, 3)
                ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-3, 2),
                        CoordinatePoint.create(1, -3),
                        CoordinatePoint.create(3, -5.5d)
                ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-1, 4),
                        CoordinatePoint.create(2, 5),
                        CoordinatePoint.create(1, 14d/3d)
                ))));

    }

    @Test
    public void droughtWeatherTest() {

        final WeatherTypeEnum WEATHER_EXPECTED = WeatherTypeEnum.DROUGHT;

        // Aligned pointers with center
        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(3, 3)
                ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 1),
                        CoordinatePoint.create(2, 2),
                        CoordinatePoint.create(-3, -3)
                ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(0, 1),
                        CoordinatePoint.create(0, 2),
                        CoordinatePoint.create(0, -3)
                ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(1, 0),
                        CoordinatePoint.create(5, 0),
                        CoordinatePoint.create(-100, 0)
                ))));

    }

    @Test
    public void rainWeatherTest() {

        // Triangle pointers with center
        WeatherTypeEnum WEATHER_EXPECTED = WeatherTypeEnum.RAIN;

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-3, 7),
                        CoordinatePoint.create(1, -5),
                        CoordinatePoint.create(5, 4))
                )));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-6, 1),
                        CoordinatePoint.create(-2, -6),
                        CoordinatePoint.create(3, 7)
                ))));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(0, -6),
                        CoordinatePoint.create(-7, 1),
                        CoordinatePoint.create(5, 1))
                )));

    }

    @Test
    public void neutralWeatherTest() {

        // Triangle pointers with NO center
        WeatherTypeEnum WEATHER_EXPECTED = WeatherTypeEnum.NEUTRAL;

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-2, 1),
                        CoordinatePoint.create(6, -1),
                        CoordinatePoint.create(4, 6))
                )));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-2, 5),
                        CoordinatePoint.create(6, 6),
                        CoordinatePoint.create(5, -4))
                )));

        assertEquals(WEATHER_EXPECTED,
                planetWeatherService.getWeather(new ArrayList<>(Arrays.asList(
                        CoordinatePoint.create(-6, 1),
                        CoordinatePoint.create(-1, -2),
                        CoordinatePoint.create(3, 7)
                ))));

    }

}
