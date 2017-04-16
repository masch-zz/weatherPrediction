package org.masch.exercise.planet.orbit.service.planet;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Value;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.PlanetSpringContextTest;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionReport;
import org.masch.exercise.planet.orbit.service.PlanetWeatherPredictionReportService;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionHistoryReport;

public class PlanetWeatherPredictionReportServiceTest extends PlanetSpringContextTest {

    @Inject
    private PlanetWeatherPredictionReportService planetWeatherPredictionReportService;

    @Value("${example.report.name}")
    private String reportName;

    @Test
    public void validateReportML() {

        Optional<PlanetWeatherPredictionReport> optReportML = planetWeatherPredictionReportService.getReportByName(reportName);

        assertTrue(optReportML.isPresent());
        PlanetWeatherPredictionReport reportML = optReportML.orElse(null);
        assertEquals(reportName, reportML.getName());
        assertNotNull(reportML.getEndTimeStamp());
        assertNotNull(reportML.getStartTimeStamp());
        assertNotNull(reportML.getWeatherPredictionHistories());
        assertWeatherCounter(365 * 10, reportML.getWeatherPredictionHistories(),
                2459, 1, 11, 1178, 1);

    }

    @Test
    public void validateWeatherDayPrediction() {

        assertWeatherDay(WeatherTypeEnum.DROUGHT, reportName, 0);
        assertWeatherDay(WeatherTypeEnum.OPTIMAL, reportName, 229);
        assertWeatherDay(WeatherTypeEnum.NEUTRAL, reportName, 1523);
        assertWeatherDay(WeatherTypeEnum.MAX_RAIN, reportName, 3387);
        assertWeatherDay(WeatherTypeEnum.RAIN, reportName, 3619);

    }

    private void assertWeatherDay(WeatherTypeEnum weatherTypeExpected, String reportName, long numberDay) {

        Optional<PlanetWeatherPredictionHistoryReport> optPlanetWeatherPredictionHistoryReport = planetWeatherPredictionReportService
                .getLastHistoryReportReportByNameAndDay(reportName, numberDay);

        assertTrue(optPlanetWeatherPredictionHistoryReport.isPresent());
        PlanetWeatherPredictionHistoryReport planetWeatherPredictionHistoryReport = optPlanetWeatherPredictionHistoryReport.orElse(null);
        assertEquals(numberDay, planetWeatherPredictionHistoryReport.getDay().longValue());
        assertEquals(weatherTypeExpected, planetWeatherPredictionHistoryReport.getWeather());

    }

    private void assertWeatherCounter(int expectedCounterList, List<PlanetWeatherPredictionHistoryReport> weatherPredictionHistories,
                                      int neutralWeatherCount, int droughtWeatherCount, int optimalWeatherCount,
                                      int rainWeatherCount, int maxRainWeatherCount) {

        assertNotNull(weatherPredictionHistories);
        assertEquals(expectedCounterList, weatherPredictionHistories.size());
        assertWeatherCounter(rainWeatherCount, weatherPredictionHistories, WeatherTypeEnum.RAIN);
        assertWeatherCounter(neutralWeatherCount, weatherPredictionHistories, WeatherTypeEnum.NEUTRAL);
        assertWeatherCounter(droughtWeatherCount, weatherPredictionHistories, WeatherTypeEnum.DROUGHT);
        assertWeatherCounter(optimalWeatherCount, weatherPredictionHistories, WeatherTypeEnum.OPTIMAL);
        assertWeatherCounter(maxRainWeatherCount, weatherPredictionHistories, WeatherTypeEnum.MAX_RAIN);

    }

    private void assertWeatherCounter(int expected, List<PlanetWeatherPredictionHistoryReport> weatherPredictionHistories, WeatherTypeEnum weatherToCheck) {
        long sizeWeather = weatherPredictionHistories.stream().filter(weather -> weather.getWeather().equals(weatherToCheck)).count();
        assertEquals(weatherToCheck +  " weather. Expected: " + expected + " Actual: " + sizeWeather, expected, sizeWeather);
    }

}
