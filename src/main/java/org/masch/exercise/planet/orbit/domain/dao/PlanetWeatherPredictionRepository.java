package org.masch.exercise.planet.orbit.domain.dao;

import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlanetWeatherPredictionRepository extends CrudRepository<PlanetWeatherPredictionEntity, Long> {

    PlanetWeatherPredictionEntity findByDay(Long i);

}
