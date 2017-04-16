package org.masch.exercise.planet.orbit.domain.dto;

import java.util.Date;
import java.util.List;

public class PlanetWeatherPredictionReport {

    private String name;
    private Date endTimeStamp;
    private Date startTimeStamp;
    private List<PlanetWeatherPredictionHistoryReport> planetWeatherPredictionHistoriesReport;

    public static PlanetWeatherPredictionReport create(String name, Date startTimeStamp, Date endTimeStamp, List<PlanetWeatherPredictionHistoryReport> planetWeatherPredictionHistoriesReport) {
        return new PlanetWeatherPredictionReport(name, startTimeStamp, endTimeStamp, planetWeatherPredictionHistoriesReport);
    }

    private PlanetWeatherPredictionReport(String name, Date startTimeStamp, Date endTimeStamp, List<PlanetWeatherPredictionHistoryReport> planetWeatherPredictionHistoriesReport) {
        this.name = name;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
        this.planetWeatherPredictionHistoriesReport = planetWeatherPredictionHistoriesReport;
    }

    public String getName() {
        return name;
    }

    public Date getEndTimeStamp() {
        return endTimeStamp;
    }

    public Date getStartTimeStamp() {
        return startTimeStamp;
    }

    public List<PlanetWeatherPredictionHistoryReport> getWeatherPredictionHistories() {
        return planetWeatherPredictionHistoriesReport;
    }
}
