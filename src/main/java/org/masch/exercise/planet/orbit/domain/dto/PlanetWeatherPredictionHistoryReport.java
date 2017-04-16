package org.masch.exercise.planet.orbit.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.domain.serializer.PlanetWeatherPredictionHistoryReportSerialized;

@JsonSerialize(using = PlanetWeatherPredictionHistoryReportSerialized.class)
public class PlanetWeatherPredictionHistoryReport {

    private Long day;

    private WeatherTypeEnum weather;

    public static PlanetWeatherPredictionHistoryReport create(Long day, WeatherTypeEnum weather) {
        return new PlanetWeatherPredictionHistoryReport(day, weather);
    }

    private PlanetWeatherPredictionHistoryReport(Long day, WeatherTypeEnum weather) {
        this.day = day;
        this.weather = weather;
    }

    public Long getDay() {
        return day;
    }

    public WeatherTypeEnum getWeather() {
        return weather;
    }
}
