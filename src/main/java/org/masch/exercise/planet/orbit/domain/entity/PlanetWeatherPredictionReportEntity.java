package org.masch.exercise.planet.orbit.domain.entity;

import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class PlanetWeatherPredictionReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private Date startTimeStamp;

    @Column
    private Date endTimeStamp;

    public static PlanetWeatherPredictionReportEntity create(String name, Date startTimeStamp, Date endTimeStamp) {
        return new PlanetWeatherPredictionReportEntity(null, name, startTimeStamp, endTimeStamp);
    }

    private PlanetWeatherPredictionReportEntity() {
    }

    private PlanetWeatherPredictionReportEntity(Long id, String name, Date startTimeStamp, Date endTimeStamp) {
        this.id = id;
        this.name = name;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartTimeStamp() {
        return startTimeStamp;
    }

    public Date getEndTimeStamp() {
        return endTimeStamp;
    }

}
