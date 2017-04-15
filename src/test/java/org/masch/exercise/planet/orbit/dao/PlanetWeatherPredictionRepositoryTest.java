package org.masch.exercise.planet.orbit.dao;

import javax.annotation.Resource;
import java.util.stream.LongStream;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.domain.entity.PlanetEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionRepository;

public class PlanetWeatherPredictionRepositoryTest extends PlanetSpringContextTest {

    @Resource
    private PlanetRepository planetRepository;

    @Resource
    private PlanetWeatherPredictionRepository planetWeatherPredictionRepository;

    @Test
    public void saveAndFindByDay() {

        PlanetEntity planet = planetRepository.save(PlanetEntity.create("Planet"));

        LongStream.rangeClosed(1, 10).forEach( i-> {
            PlanetWeatherPredictionEntity planetWeatherPredictionEntity = planetWeatherPredictionRepository.save(
                    PlanetWeatherPredictionEntity.create(i, planet, WeatherTypeEnum.DROUGHT));
            assertNotNull(planetWeatherPredictionEntity);
        });

        LongStream.rangeClosed(1, 10).forEach( i-> {
            PlanetWeatherPredictionEntity planetWeatherPredictionEntity =  planetWeatherPredictionRepository.findByDay(i);
            assertNotNull(planetWeatherPredictionEntity);
        });

    }
}
