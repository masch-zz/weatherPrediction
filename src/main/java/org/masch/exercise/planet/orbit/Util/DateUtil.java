package org.masch.exercise.planet.orbit.Util;

import java.util.Date;
import java.time.ZoneId;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

    public static Date nowAsDate() {
        return asDate(LocalDateTime.now());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
