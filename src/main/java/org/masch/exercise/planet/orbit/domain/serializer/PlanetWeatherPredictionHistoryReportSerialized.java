package org.masch.exercise.planet.orbit.domain.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionHistoryReport;

public class PlanetWeatherPredictionHistoryReportSerialized extends StdSerializer<PlanetWeatherPredictionHistoryReport> {

    private static final String DAY_FIELD_NAME = "dia";
    private static final String WEATHER_FIELD_NAME = "clima";
    private static final String RAIN_CONVERSION_VALUE = "lluvia";
    private static final String OPTIMAL_CONVERSION_VALUE = "optimo";
    private static final String DROUGHT_CONVERSION_VALUE = "sequia";
    private static final String NEUTRAL_CONVERSION_VALUE = "neutral";
    private static final String UNKNOWN_CONVERSION_VALUE = "descononido";
    private static final String MAX_RAIN_CONVERSION_VALUE = "máxima lluvia";

    public PlanetWeatherPredictionHistoryReportSerialized() {
        super(PlanetWeatherPredictionHistoryReport.class);
    }

    @Override
    public void serialize(PlanetWeatherPredictionHistoryReport planetWeatherPredictionHistoryReport, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();

        gen.writeFieldName(DAY_FIELD_NAME);
        gen.writeNumber(planetWeatherPredictionHistoryReport.getDay());

        gen.writeFieldName(WEATHER_FIELD_NAME);
        gen.writeString(convertWeatherTypeName(planetWeatherPredictionHistoryReport.getWeather()));

        gen.writeEndObject();
    }

    private String convertWeatherTypeName(WeatherTypeEnum weatherType) {

        switch (weatherType) {
            case DROUGHT:
                return DROUGHT_CONVERSION_VALUE;
            case MAX_RAIN:
                return MAX_RAIN_CONVERSION_VALUE;
            case NEUTRAL:
                return NEUTRAL_CONVERSION_VALUE;
            case OPTIMAL:
                return OPTIMAL_CONVERSION_VALUE;
            case RAIN:
                return RAIN_CONVERSION_VALUE;
            default:
                return UNKNOWN_CONVERSION_VALUE;
        }
    }

}
