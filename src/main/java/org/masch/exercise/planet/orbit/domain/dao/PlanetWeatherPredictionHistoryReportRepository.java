package org.masch.exercise.planet.orbit.domain.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionReportEntity;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionHistoryReportEntity;

public interface PlanetWeatherPredictionHistoryReportRepository extends CrudRepository<PlanetWeatherPredictionHistoryReportEntity, Long> {

    List<PlanetWeatherPredictionHistoryReportEntity> findByPlanetWeatherPredictionReportEntityOrderByDay(PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity);

}
