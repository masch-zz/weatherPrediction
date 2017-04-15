package org.masch.exercise.planet.orbit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionReportEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionReportRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionHistoryReportEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionHistoryReportRepository;

@Configuration
@EntityScan(basePackageClasses = {
        Planet.class,
        PlanetWeatherPredictionReportEntity.class,
        PlanetWeatherPredictionHistoryReportEntity.class})
@EnableJpaRepositories(basePackageClasses = {
        PlanetRepository.class,
        PlanetWeatherPredictionReportRepository.class,
        PlanetWeatherPredictionHistoryReportRepository.class})
public class PersistenceConfiguration {

}
