package org.masch.exercise.planet.orbit.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicLong;

import org.masch.exercise.planet.orbit.Util.DateUtil;
import org.masch.exercise.planet.orbit.domain.dto.Planet;
import org.masch.exercise.planet.orbit.domain.dao.PlanetRepository;
import org.masch.exercise.planet.orbit.domain.dto.WeatherPrediction;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionReport;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionHistoryReport;
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

    void savePredictions(String reportName, List<Planet> planets, int amountDaysMovements) {

        /*PlanetEntity ferengiPlanet = planetRepository.save(PlanetEntity.create("Ferengi", 500, true, 1));
        PlanetEntity vulcanoPlanet = planetRepository.save(PlanetEntity.create("Vulcano", 1000, false, 5));
        PlanetEntity betasoidePlanet = planetRepository.save(PlanetEntity.create("Betasoide", 2000, true, 3));*/

        Date startTimeStampReport = DateUtil.nowAsDate();
        List<WeatherPrediction> weatherPredictions = planetWeatherService.predictWeather(planets, amountDaysMovements);
        Date endTimeStampReport = DateUtil.nowAsDate();

        PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity =
                planetWeatherPredictionReportRepository.save(PlanetWeatherPredictionReportEntity.create(reportName, startTimeStampReport, endTimeStampReport));

        AtomicLong day = new AtomicLong();
        weatherPredictions.forEach(weatherPrediction ->  {

            PlanetWeatherPredictionHistoryReportEntity planetWeatherPredictionHistoryReportEntity = PlanetWeatherPredictionHistoryReportEntity.create(
                    day.getAndIncrement(), weatherPrediction.getWeatherTypeEnum(), planetWeatherPredictionReportEntity);
            planetWeatherPredictionHistoryReportRepository.save(planetWeatherPredictionHistoryReportEntity);

        });

    }

    public Optional<PlanetWeatherPredictionReport> getReportByName(String reportName) {
        Optional<PlanetWeatherPredictionReportEntity> optReportByNameEntity = getPlanetWeatherPredictionReportByName(reportName);

        if (!optReportByNameEntity.isPresent())
            return Optional.empty();

        PlanetWeatherPredictionReportEntity reportByNameEntity = optReportByNameEntity.get();

        List<PlanetWeatherPredictionHistoryReportEntity> planetWeatherPredictionHistoriesEntity = planetWeatherPredictionHistoryReportRepository.findByPlanetWeatherPredictionReportEntityOrderByDay(reportByNameEntity);

        List<PlanetWeatherPredictionHistoryReport> planetWeatherPredictionHistoriesReport = planetWeatherPredictionHistoriesEntity.stream()
                .map(planetWeatherPredictionHistoryEntity ->
                        PlanetWeatherPredictionHistoryReport.create(planetWeatherPredictionHistoryEntity.getDay(), planetWeatherPredictionHistoryEntity.getWeather()))
                .collect(Collectors.toList());

        return Optional.of(PlanetWeatherPredictionReport.create(reportName,
                reportByNameEntity.getStartTimeStamp(), reportByNameEntity.getEndTimeStamp(), planetWeatherPredictionHistoriesReport));
    }

    public Optional<PlanetWeatherPredictionHistoryReport> getLastHistoryReportReportByNameAndDay(String reportName, long numberDay) {

        Optional<PlanetWeatherPredictionReportEntity> optReportEntity = getPlanetWeatherPredictionReportByName(reportName);

        if (!optReportEntity.isPresent())
            return Optional.empty();

        Optional<PlanetWeatherPredictionHistoryReportEntity> optPlanetWeatherPredictionReportEntityAndDay = planetWeatherPredictionHistoryReportRepository.findByPlanetWeatherPredictionReportEntityAndDay(optReportEntity.get(), numberDay);
        if (!optPlanetWeatherPredictionReportEntityAndDay.isPresent())
            return Optional.empty();

        PlanetWeatherPredictionHistoryReportEntity planetWeatherPredictionHistoryReportEntity = optPlanetWeatherPredictionReportEntityAndDay.get();

        return Optional.of(PlanetWeatherPredictionHistoryReport.create(
                planetWeatherPredictionHistoryReportEntity.getDay(), planetWeatherPredictionHistoryReportEntity.getWeather()));
    }

    private Optional<PlanetWeatherPredictionReportEntity> getPlanetWeatherPredictionReportByName(String reportName) {
        return planetWeatherPredictionReportRepository.findByName(reportName);
    }

}
