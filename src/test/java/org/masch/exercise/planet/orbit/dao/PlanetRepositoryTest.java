package org.masch.exercise.planet.orbit.dao;

import java.util.stream.IntStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;

import org.masch.exercise.planet.orbit.PlanetSpringContextTest;
import org.masch.exercise.planet.orbit.domain.entity.PlanetEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;

import javax.inject.Inject;

public class PlanetRepositoryTest extends PlanetSpringContextTest {

    @Inject
    private PlanetRepository planetRepository;

    private String getPlanetName(String prefixName, Integer index) {
        return prefixName + index;
    }

    @Test
    public void saveAndFindById() {

        double radius = 500;
        boolean clockWiseRotation = true;
        double degreeAngularVelocity = 45;
        String planetPrefixName = "Planet";

        IntStream.rangeClosed(1, 10).forEach(i -> {
            planetRepository.save(PlanetEntity.create(getPlanetName(planetPrefixName, i), radius, clockWiseRotation, degreeAngularVelocity));
        });

        IntStream.rangeClosed(1, 10).forEach(i -> {
            PlanetEntity planet = planetRepository.findByName(getPlanetName(planetPrefixName, i));
            assertNotNull(planet);
            assertEquals(radius, planet.getRadius(), 0);
            assertEquals(planetPrefixName + i, planet.getName());
            assertEquals(clockWiseRotation, planet.isClockWiseRotation());
            assertEquals(degreeAngularVelocity, planet.getDegreeAngularVelocity(), 0);
        });

    }

}
