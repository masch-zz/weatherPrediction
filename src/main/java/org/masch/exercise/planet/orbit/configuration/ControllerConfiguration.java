package org.masch.exercise.planet.orbit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.masch.exercise.planet.orbit.controller.PlanetWeatherController;
import org.masch.exercise.planet.orbit.service.PlanetWeatherPredictionReportService;

@Configuration
public class ControllerConfiguration {

    @Bean
    public PlanetWeatherController planetWeatherController(PlanetWeatherPredictionReportService planetWeatherPredictionReportService) {
        return PlanetWeatherController.create(planetWeatherPredictionReportService);
    }

}
