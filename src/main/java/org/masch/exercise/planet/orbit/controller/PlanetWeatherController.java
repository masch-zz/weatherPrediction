package org.masch.exercise.planet.orbit.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.masch.exercise.planet.orbit.service.PlanetWeatherPredictionReportService;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionHistoryReport;

@RestController
public class PlanetWeatherController {

    private final String DEFAULT_REPORT_NAME = "ML Solar system";
    private PlanetWeatherPredictionReportService planetWeatherPredictionReportService;

    public static PlanetWeatherController create(PlanetWeatherPredictionReportService planetWeatherPredictionReportService) {
        return new PlanetWeatherController(planetWeatherPredictionReportService);
    }

    private PlanetWeatherController(PlanetWeatherPredictionReportService planetWeatherPredictionReportService) {
        this.planetWeatherPredictionReportService = planetWeatherPredictionReportService;
    }

    @RequestMapping("/clima")
    public PlanetWeatherPredictionHistoryReport weatherByDay(@RequestParam(value = "nombreReporte", defaultValue = DEFAULT_REPORT_NAME) String reportName,
                                                             @RequestParam(value = "dia") long numberDay) {

        Optional<PlanetWeatherPredictionHistoryReport> historyReportDay = planetWeatherPredictionReportService
                .getLastHistoryReportReportByNameAndDay(reportName, numberDay);

        return historyReportDay.orElse(null);
    }

}
