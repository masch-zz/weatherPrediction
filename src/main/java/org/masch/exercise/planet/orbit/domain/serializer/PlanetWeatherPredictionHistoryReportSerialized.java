package org.masch.exercise.planet.orbit.domain.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import org.masch.exercise.planet.orbit.enums.WeatherTypeEnum;
import org.masch.exercise.planet.orbit.domain.dto.PlanetWeatherPredictionHistoryReport;

public class PlanetWeatherPredictionHistoryReportSerialized extends StdSerializer {

    public PlanetWeatherPredictionHistoryReportSerialized() {
        super(PlanetWeatherPredictionHistoryReport.class);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        PlanetWeatherPredictionHistoryReport planetWeatherPredictionHistoryReport = (PlanetWeatherPredictionHistoryReport) value;
        gen.writeStartObject();

        gen.writeFieldName("dia");
        gen.writeNumber(planetWeatherPredictionHistoryReport.getDay());

        gen.writeFieldName("clima");
        gen.writeString(convertWeatherTypeName(planetWeatherPredictionHistoryReport.getWeather()));

        gen.writeEndObject();
    }

    private String convertWeatherTypeName(WeatherTypeEnum weatherType) {

        switch (weatherType) {
            case DROUGHT:
                return "sequia";
            case MAX_RAIN:
                return "máxima lluvia";
            case NEUTRAL:
                return "neutral";
            case OPTIMAL:
                return "optimo";
            case RAIN:
                return "lluvia";
            default:
                return "descononido";
        }
    }

}
