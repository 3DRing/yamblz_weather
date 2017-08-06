package tljfn.yamblzweather.model.db;

import java.util.List;

import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.model.api.data.weather.Main;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.api.data.weather.Weather;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.errors.RawToDBConvertingException;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 06.08.17.
 */

public class DBConverter {

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

    public static DBCity fromRawCity(RawCity city) {
        if (city.yaId <= 0) {
            throw new RawToDBConvertingException("Incorrect yaId in city");
        }
        if (city.openWeatherId <= 0) {
            throw new RawToDBConvertingException("Incorrect open weather id in city");
        }
        if (city.country == null || city.country.isEmpty()) {
            throw new RawToDBConvertingException("Incorrect country code in city");
        }
        if (city.enName == null) {
            throw new RawToDBConvertingException("Incorrect eng name in city");
        }
        if (city.ruName == null) {
            throw new RawToDBConvertingException("Incorrect rus name in city");
        }

        return new DBCity.Builder()
                .id(city.yaId)
                .openWeatherId(city.openWeatherId)
                .ruName(city.ruName.toLowerCase())
                .enName(city.enName.toLowerCase())
                .countryCode(city.country.toLowerCase())
                .favorite(false)
                .build();
    }
}
