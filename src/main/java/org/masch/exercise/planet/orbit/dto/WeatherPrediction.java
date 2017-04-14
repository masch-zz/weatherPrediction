package org.masch.exercise.planet.orbit.dto;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;

public class WeatherPrediction {

    private double perimeter;
    private WeatherTypeEnum weatherTypeEnum;

    public static WeatherPrediction create(double perimeter, WeatherTypeEnum weatherTypeEnum) {
        return new WeatherPrediction(perimeter, weatherTypeEnum);
    }

    private WeatherPrediction(double perimeter, WeatherTypeEnum weatherTypeEnum) {
        this.perimeter = perimeter;
        this.weatherTypeEnum = weatherTypeEnum;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public WeatherTypeEnum getWeatherTypeEnum() {
        return weatherTypeEnum;
    }

    public void setWeatherTypeEnum(WeatherTypeEnum weatherTypeEnum) {
        this.weatherTypeEnum = weatherTypeEnum;
    }

}
