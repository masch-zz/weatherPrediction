package org.masch.exercise.planet.orbit.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;

@Entity
public class PlanetWeatherPredictionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToOne
    private PlanetEntity planet;

    @Column
    private Long day;

    @Column
    @Enumerated(EnumType.STRING)
    private WeatherTypeEnum weather;

    public static PlanetWeatherPredictionEntity create(Long day, PlanetEntity planet, WeatherTypeEnum weather) {
        return new PlanetWeatherPredictionEntity(null, day, planet, weather);
    }

    private PlanetWeatherPredictionEntity() {
    }

    public PlanetWeatherPredictionEntity(Long id, Long day, PlanetEntity planet, WeatherTypeEnum weather) {
        this.id = id;
        this.day = day;
        this.planet = planet;
        this.weather = weather;
    }
}
