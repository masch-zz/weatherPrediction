package org.masch.exercise.planet.orbit.dao;

import java.util.List;
import javax.inject.Inject;
import java.util.stream.LongStream;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.masch.exercise.planet.orbit.Util.DateUtil;
import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.PlanetSpringContextTest;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionReportRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionReportEntity;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionHistoryReportEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionHistoryReportRepository;

public class PlanetWeatherPredictionHistoryReportRepositoryTest extends PlanetSpringContextTest {

    @Inject
    private PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository;

    @Inject
    private PlanetWeatherPredictionHistoryReportRepository planetWeatherPredictionHistoryReportRepository;

    @Test
    public void saveAndFindByDay() {

        int maxReportHistoryDay = 10;
        PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity =
                planetWeatherPredictionReportRepository.save(PlanetWeatherPredictionReportEntity.create(
                "Report-PlanetWeatherPredictionHistoryReportRepositoryTest", DateUtil.nowAsDate(), DateUtil.nowAsDate()));

        LongStream.rangeClosed(1, maxReportHistoryDay).forEach( i-> {
            PlanetWeatherPredictionHistoryReportEntity planetWeatherPredictionHistoryReportEntity = planetWeatherPredictionHistoryReportRepository.save(
                    PlanetWeatherPredictionHistoryReportEntity.create(i, WeatherTypeEnum.DROUGHT, planetWeatherPredictionReportEntity));
            assertNotNull(planetWeatherPredictionHistoryReportEntity);
        });

        List<PlanetWeatherPredictionHistoryReportEntity> planetWeatherPredictionHistoriesReport = planetWeatherPredictionHistoryReportRepository.findByPlanetWeatherPredictionReportEntityOrderByDay(planetWeatherPredictionReportEntity);
        assertNotNull(planetWeatherPredictionHistoriesReport);
        assertEquals(maxReportHistoryDay, planetWeatherPredictionHistoriesReport.size());

        for (int i = 0; i < planetWeatherPredictionHistoriesReport.size(); i++) {
            PlanetWeatherPredictionHistoryReportEntity planetWeatherPredictionHistoryReportEntity =
                    planetWeatherPredictionHistoriesReport.get(i);
            assertEquals(i+1, planetWeatherPredictionHistoryReportEntity.getDay().longValue());
            assertEquals(WeatherTypeEnum.DROUGHT, planetWeatherPredictionHistoryReportEntity.getWeather());
        }


    }
}
