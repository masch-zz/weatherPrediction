package org.masch.exercise.planet.orbit.dao;

import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.masch.exercise.planet.orbit.Util.DateUtil;
import org.masch.exercise.planet.orbit.PlanetSpringContextTest;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionReportRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionReportEntity;

public class PlanetWeatherPredictionReportRepositoryTest extends PlanetSpringContextTest {

    @Inject
    private PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository;

    @Test
    public void test() {

        PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity = PlanetWeatherPredictionReportEntity.create(
                "Report-PlanetWeatherPredictionReportRepositoryTest", DateUtil.nowAsDate(), DateUtil.nowAsDate());

        planetWeatherPredictionReportEntity = planetWeatherPredictionReportRepository.save(planetWeatherPredictionReportEntity);

        PlanetWeatherPredictionReportEntity planetSearched = planetWeatherPredictionReportRepository.findByName(planetWeatherPredictionReportEntity.getName());
        assertNotNull(planetSearched);
        assertEquals(planetSearched.getId(), planetWeatherPredictionReportEntity.getId());
        assertEquals(planetSearched.getName(), planetWeatherPredictionReportEntity.getName());
        assertEquals(planetSearched.getEndTimeStamp().getTime(), planetWeatherPredictionReportEntity.getEndTimeStamp().getTime());
        assertEquals(planetSearched.getStartTimeStamp().getTime(), planetWeatherPredictionReportEntity.getStartTimeStamp().getTime());

    }

}
