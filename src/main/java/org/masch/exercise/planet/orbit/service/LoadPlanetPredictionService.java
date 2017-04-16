package org.masch.exercise.planet.orbit.service;

import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.beans.factory.InitializingBean;

import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.springframework.beans.factory.annotation.Value;

public class LoadPlanetPredictionService implements InitializingBean {

    @Value("${example.report.name}")
    private String reportName;

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

        ArrayList<Planet> planets = new ArrayList<>(Arrays.asList(
                Planet.create("Ferengi", 500, true, 1),
                Planet.create("Betasoide", 2000, true, 3),
                Planet.create("Vulcano", 1000, false, 5)));

        planetWeatherPredictionReportService.savePredictions(reportName, planets, amountDaysMovements);

    }
}
