package org.masch.exercise.planet.orbit.service.planet;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.masch.exercise.planet.orbit.dto.Planet;
import org.masch.exercise.planet.orbit.service.OrbitService;
import org.masch.exercise.planet.orbit.service.PointService;
import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.service.PlanetWeatherService;

public class PlanetWeatherTest {

    private PlanetWeatherService planetWeatherService = PlanetWeatherService.create(
            OrbitService.create(),
            PointService.create()
    );

    @Test
    public void planetsWith90AngularVelocityDegree() {

        int amountMovements = 4;
        List<WeatherTypeEnum> predictionsWeather = planetWeatherService.predictWeather(new ArrayList<>(Arrays.asList(
                Planet.create("Planet1", 1, true, 90),
                Planet.create("Planet2", 5, false, 90),
                Planet.create("Planet3", 10, true, 90)))
                , amountMovements);

        assertWeatherCounter(amountMovements, predictionsWeather,
                0, 4, 0, 0);
    }

    @Test
    public void planetsWithAllDroughtWeather() {

        int amountMovements = 3;
        List<WeatherTypeEnum> predictionsWeather  = planetWeatherService.predictWeather(new ArrayList<>(Arrays.asList(
                Planet.create("Planet1", 500, true, 45),
                Planet.create("Planet2", 1000, true, 45),
                Planet.create("Planet3", 5000, true, 45)))
                , amountMovements);

        assertWeatherCounter(amountMovements, predictionsWeather,
                0, 3, 0, 0);

    }

    @Test
    public void oneYear3PlanetsTest() {

        int amountDaysMovements = 10 * 365; // 10 years
        List<WeatherTypeEnum> predictionsWeather = planetWeatherService.predictWeather(new ArrayList<>(Arrays.asList(
                Planet.create("Ferengi", 500, true, 1),
                Planet.create("Betasoide", 2000, true, 3),
                Planet.create("Vulcano", 1000, false, 5)))
                , amountDaysMovements);

        assertWeatherCounter(amountDaysMovements, predictionsWeather,
                2459, 1, 11, 1179);
    }

    private void assertWeatherCounter(int expectedCounterList, List<WeatherTypeEnum> weatherList,
                                      int neutralWeatherCount, int droughtWeatherCount, int optimalWeatherCount, int rainWeatherCount) {

        assertNotNull(weatherList);
        assertEquals(expectedCounterList, weatherList.size());
        assertWeatherCounter(rainWeatherCount, weatherList, WeatherTypeEnum.RAIN);
        assertWeatherCounter(neutralWeatherCount, weatherList, WeatherTypeEnum.NEUTRAL);
        assertWeatherCounter(droughtWeatherCount, weatherList, WeatherTypeEnum.DROUGHT);
        assertWeatherCounter(optimalWeatherCount, weatherList, WeatherTypeEnum.OPTIMAL);

    }

    private void assertWeatherCounter(int expected, List<WeatherTypeEnum> weatherList, WeatherTypeEnum weatherToCheck) {
        long sizeWeather = weatherList.stream().filter(weather -> weather.equals(weatherToCheck)).count();
        assertEquals(weatherToCheck +  " weather. Expected: " + expected + " Actual: " + sizeWeather, expected, sizeWeather);
    }

}
