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

    private static WeatherCondition weatherIdToCondition(int id) {
        // todo rewrite it in a more clear way
        if (id >= 200 && id < 300) {
            return WeatherCondition.Thunderstorm;
        } else if (id >= 300 && id < 600) {
            return WeatherCondition.Rainy;
        } else if (id >= 600 && id < 700) {
            return WeatherCondition.Snow;
        } else if (id >= 700 && id < 800) {
            return WeatherCondition.Atmospherically;
        } else if (id == 800) {
            return WeatherCondition.Clear;
        } else if (id > 800 && id < 900) {
            return WeatherCondition.Cloudy;
        } else if (id >= 900 && id < 950) {
            return WeatherCondition.Extreme;
        } else if (id >= 957 && id <= 962) {
            return WeatherCondition.Windy;
        } else if (id >= 951 && id <= 956) {
            return WeatherCondition.Calm;
        } else {
            return WeatherCondition.Other;
        }
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

    public static UIWeatherData toUIWeatherData(DBWeatherData weather) {
        UIWeatherData data = new UIWeatherData.Builder()
                .city(weather.getCity())
                .temperature(weather.getTemperature())
                .time(weather.getTime())
                .condition(weatherIdToCondition(weather.getCondition()))
                .build();
        return data;
    }

}
