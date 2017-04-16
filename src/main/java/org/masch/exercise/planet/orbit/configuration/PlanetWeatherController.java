package org.masch.exercise.planet.orbit.configuration;

import java.util.Optional;
import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.masch.exercise.planet.orbit.service.PlanetWeatherPredictionReportService;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionHistoryReport;

@RestController
public class PlanetWeatherController {

    @Inject
    private PlanetWeatherPredictionReportService planetWeatherPredictionReportService;

    @RequestMapping("/clima")
    public PlanetWeatherPredictionHistoryReport weatherByDay(@RequestParam(value = "nombreReporte", defaultValue = "Solar system ML") String reportName,
                                                             @RequestParam(value = "dia") long numberDay) {

        Optional<PlanetWeatherPredictionHistoryReport> historyReportDay = planetWeatherPredictionReportService
                .getLastHistoryReportReportByNameAndDay(reportName, numberDay);

        return historyReportDay.orElse(null);
    }

}
