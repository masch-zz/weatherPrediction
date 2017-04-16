package org.masch.exercise.planet.orbit.domain.dto;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;

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
