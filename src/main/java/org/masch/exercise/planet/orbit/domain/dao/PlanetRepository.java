package org.masch.exercise.planet.orbit.domain.dao;

import org.springframework.data.repository.CrudRepository;

import org.masch.exercise.planet.orbit.domain.entity.PlanetEntity;

public interface PlanetRepository extends CrudRepository<PlanetEntity, Long> {

}
