package org.masch.exercise.planet.orbit.service;

import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;

import org.masch.exercise.planet.orbit.domain.dto.Planet;

public class LoadPlanetPredictionService implements InitializingBean {

    private PlanetWeatherPredictionReportService planetWeatherPredictionReportService;

    public static LoadPlanetPredictionService create(PlanetWeatherPredictionReportService planetWeatherPredictionReportService) {
        return new LoadPlanetPredictionService(planetWeatherPredictionReportService);
    }

    private LoadPlanetPredictionService(PlanetWeatherPredictionReportService planetWeatherPredictionReportService) {
        this.planetWeatherPredictionReportService = planetWeatherPredictionReportService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        int amountDaysMovements = 10 * 365; // 10 years

        ArrayList<Planet> mlPlanets = new ArrayList<>(Arrays.asList(
                Planet.create("Ferengi", 500, true, 1),
                Planet.create("Betasoide", 2000, true, 3),
                Planet.create("Vulcano", 1000, false, 5)));

        planetWeatherPredictionReportService.savePredictions("ML Solar system", mlPlanets, amountDaysMovements);

    }
}
