package org.masch.exercise.planet.orbit.dao;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.masch.exercise.planet.orbit.domain.entity.PlanetEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;

public class PlanetRepositoryTest extends PlanetSpringContextTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void saveAndFindById() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            planetRepository.save(PlanetEntity.create("Planet" + i));
        });

        LongStream.rangeClosed(1, 10).forEach(i -> {
            PlanetEntity planet = planetRepository.findOne(i);
            assertNotNull(planet);
        });

    }

}
