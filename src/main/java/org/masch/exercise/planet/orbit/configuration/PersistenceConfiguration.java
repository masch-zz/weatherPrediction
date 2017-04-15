package org.masch.exercise.planet.orbit.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionRepository;

@Configuration
@EntityScan(basePackageClasses = {
        Planet.class,
        PlanetWeatherPredictionEntity.class})
@EnableJpaRepositories(basePackageClasses = {
        PlanetRepository.class,
        PlanetWeatherPredictionRepository.class})
public class PersistenceConfiguration {

}
