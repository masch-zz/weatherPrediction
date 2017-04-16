package org.masch.exercise.planet.orbit.dao;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.masch.exercise.planet.orbit.Util.DateUtil;
import org.masch.exercise.planet.orbit.PlanetSpringContextTest;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionReportEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionReportRepository;

import java.util.Optional;

public class PlanetWeatherPredictionReportRepositoryTest extends PlanetSpringContextTest {

    @Inject
    private PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository;

    @Test
    public void saveAndFind() {

        PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity = PlanetWeatherPredictionReportEntity.create(
                "Report-PlanetWeatherPredictionReportRepositoryTest", DateUtil.nowAsDate(), DateUtil.nowAsDate());

        planetWeatherPredictionReportEntity = planetWeatherPredictionReportRepository.save(planetWeatherPredictionReportEntity);

        Optional<PlanetWeatherPredictionReportEntity> optPlanetSearched = planetWeatherPredictionReportRepository.findByName(planetWeatherPredictionReportEntity.getName());
        assertTrue(optPlanetSearched.isPresent());
        PlanetWeatherPredictionReportEntity planetSearched = optPlanetSearched.orElse(null);
        assertNotNull(planetSearched);
        assertEquals(planetSearched.getId(), planetWeatherPredictionReportEntity.getId());
        assertEquals(planetSearched.getName(), planetWeatherPredictionReportEntity.getName());
        assertEquals(planetSearched.getEndTimeStamp().getTime(), planetWeatherPredictionReportEntity.getEndTimeStamp().getTime());
        assertEquals(planetSearched.getStartTimeStamp().getTime(), planetWeatherPredictionReportEntity.getStartTimeStamp().getTime());

    }

}
