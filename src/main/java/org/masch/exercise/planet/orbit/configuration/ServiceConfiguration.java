package org.masch.exercise.planet.orbit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.masch.exercise.planet.orbit.service.PointService;
import org.masch.exercise.planet.orbit.service.OrbitService;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;
import org.masch.exercise.planet.orbit.service.PlanetWeatherService;
import org.masch.exercise.planet.orbit.service.LoadPlanetPredictionService;
import org.masch.exercise.planet.orbit.service.PlanetWeatherPredictionReportService;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionReportRepository;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionHistoryReportRepository;

@Configuration
public class ServiceConfiguration {

    @Bean
    public OrbitService orbitService() {
        return OrbitService.create();
    }

    @Bean
    public PointService pointService() {
        return PointService.create();
    }

    @Bean
    public PlanetWeatherPredictionReportService planetWeatherPredictionReportService(PlanetRepository planetRepository, PlanetWeatherService planetWeatherService,
                                                                                     PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository,
                                                                                     PlanetWeatherPredictionHistoryReportRepository planetWeatherPredictionHistoryReportRepository) {
        return PlanetWeatherPredictionReportService.create(planetRepository, planetWeatherService, planetWeatherPredictionReportRepository, planetWeatherPredictionHistoryReportRepository);
    }

    @Bean
    public PlanetWeatherService planetWeatherService(OrbitService orbitService, PointService pointService) {
        return PlanetWeatherService.create(orbitService, pointService);
    }

    @Bean
    public LoadPlanetPredictionService loadPlanetPredictionService(PlanetWeatherPredictionReportService planetWeatherPredictionReportService) {
        return LoadPlanetPredictionService.create(planetWeatherPredictionReportService);
    }

}
