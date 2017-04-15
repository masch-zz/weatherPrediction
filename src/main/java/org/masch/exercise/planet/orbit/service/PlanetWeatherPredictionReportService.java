package org.masch.exercise.planet.orbit.service;

import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.masch.exercise.planet.orbit.Util.DateUtil;
import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.domain.entity.PlanetEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;
import org.masch.exercise.planet.orbit.domain.dto.WeatherPrediction;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionReportEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionReportRepository;
import org.masch.exercise.planet.orbit.domain.entity.PlanetWeatherPredictionHistoryReportEntity;
import org.masch.exercise.planet.orbit.domain.dao.PlanetWeatherPredictionHistoryReportRepository;

public class PlanetWeatherPredictionReportService {

    public static PlanetWeatherPredictionReportService create(PlanetRepository planetRepository, PlanetWeatherService planetWeatherService,
                                                              PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository,
                                                              PlanetWeatherPredictionHistoryReportRepository planetWeatherPredictionHistoryReportRepository) {
        return  new PlanetWeatherPredictionReportService(planetRepository, planetWeatherService, planetWeatherPredictionReportRepository, planetWeatherPredictionHistoryReportRepository);
    }

    private PlanetWeatherPredictionReportService(PlanetRepository planetRepository, PlanetWeatherService planetWeatherService,
                                                 PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository,
                                                 PlanetWeatherPredictionHistoryReportRepository planetWeatherPredictionHistoryReportRepository) {
        this.planetRepository = planetRepository;
        this.planetWeatherService = planetWeatherService;
        this.planetWeatherPredictionReportRepository = planetWeatherPredictionReportRepository;
        this.planetWeatherPredictionHistoryReportRepository = planetWeatherPredictionHistoryReportRepository;
    }

    private PlanetRepository planetRepository;
    private PlanetWeatherService planetWeatherService;
    private PlanetWeatherPredictionReportRepository planetWeatherPredictionReportRepository;
    private PlanetWeatherPredictionHistoryReportRepository planetWeatherPredictionHistoryReportRepository;

    public void savePredictions(String reportName, List<Planet> planets, int amountDaysMovements) {

        /*PlanetEntity ferengiPlanet = planetRepository.save(PlanetEntity.create("Ferengi", 500, true, 1));
        PlanetEntity vulcanoPlanet = planetRepository.save(PlanetEntity.create("Vulcano", 1000, false, 5));
        PlanetEntity betasoidePlanet = planetRepository.save(PlanetEntity.create("Betasoide", 2000, true, 3));*/

        Date startTimeStampReport = DateUtil.nowAsDate();
        List<WeatherPrediction> weatherPredictions = planetWeatherService.predictWeather(planets, amountDaysMovements);
        Date endTimeStampReport = DateUtil.nowAsDate();

        PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity =
                planetWeatherPredictionReportRepository.save(PlanetWeatherPredictionReportEntity.create(reportName, startTimeStampReport, endTimeStampReport));

        AtomicLong day = new AtomicLong();
        weatherPredictions.stream().forEach(weatherPrediction ->  {

            PlanetWeatherPredictionHistoryReportEntity planetWeatherPredictionHistoryReportEntity = PlanetWeatherPredictionHistoryReportEntity.create(
                    day.incrementAndGet(), weatherPrediction.getWeatherTypeEnum(), planetWeatherPredictionReportEntity);
            planetWeatherPredictionHistoryReportRepository.save(planetWeatherPredictionHistoryReportEntity);

        });

    }
}
