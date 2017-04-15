package org.masch.exercise.planet.orbit.domain.entity;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;

@Entity
public class PlanetWeatherPredictionHistoryReportEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private Long day;

    @Column
    @Enumerated(EnumType.STRING)
    private WeatherTypeEnum weather;

    @OneToOne
    private PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity;

    public static PlanetWeatherPredictionHistoryReportEntity create(Long day, WeatherTypeEnum weather, PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity) {
        return new PlanetWeatherPredictionHistoryReportEntity(null, day, weather, planetWeatherPredictionReportEntity);
    }

    private PlanetWeatherPredictionHistoryReportEntity() {
    }

    public PlanetWeatherPredictionHistoryReportEntity(Long id, Long day, WeatherTypeEnum weather, PlanetWeatherPredictionReportEntity planetWeatherPredictionReportEntity) {
        this.id = id;
        this.day = day;
        this.weather = weather;
        this.planetWeatherPredictionReportEntity = planetWeatherPredictionReportEntity;
    }

    public Long getId() {
        return id;
    }

    public Long getDay() {
        return day;
    }

    public WeatherTypeEnum getWeather() {
        return weather;
    }

    public PlanetWeatherPredictionReportEntity getPlanetWeatherPredictionReportEntity() {
        return planetWeatherPredictionReportEntity;
    }

}
