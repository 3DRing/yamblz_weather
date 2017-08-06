package tljfn.yamblzweather.model.db.weather;

import java.util.List;

import tljfn.yamblzweather.model.api.data.weather.Main;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.api.data.weather.Weather;
import tljfn.yamblzweather.model.errors.RawToDBConvertingException;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 06.08.17.
 */

public class DBWeatherConverter {

    private static final double KELVIN_OFFSET = 273.15;

    private static double round(double d) {
        return Math.round(d * 100) / 100D;
    }

    private static double toCelsius(double kelvins) {
        return kelvins - KELVIN_OFFSET;
    }

    public static DBWeatherData fromRawWeatherData(RawWeather weather) {
        List<Weather> weathers = weather.getWeather();
        String city = weather.getName();
        Main main = weather.getMain();

        if (weathers == null) {
            throw new RawToDBConvertingException("Cannot extract weather condition from server response");
        }
        if (city == null) {
            throw new RawToDBConvertingException("No city received from server response");
        }
        if (main == null) {
            throw new RawToDBConvertingException("No temperature received from server response");
        }

        int condition = weathers.get(0).getId();
        double temperature = round(toCelsius(weather.getMain().getTemp()));


        DBWeatherData data = new DBWeatherData.Builder()
                .city(city)
                .temperature(temperature)
                .time(System.currentTimeMillis())
                .condition(condition)
                .build();
        return data;
    }

}
