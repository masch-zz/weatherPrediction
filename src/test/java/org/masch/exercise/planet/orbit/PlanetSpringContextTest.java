package org.masch.exercise.planet.orbit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.masch.exercise.planet.orbit.configuration.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestPropertySource(locations = {
        "classpath:application.properties"})
public abstract class PlanetSpringContextTest {
}
