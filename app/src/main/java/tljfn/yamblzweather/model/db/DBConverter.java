package tljfn.yamblzweather.model.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.model.api.data.forecast.RawForecast;
import tljfn.yamblzweather.model.api.data.forecast.SingularForecast;
import tljfn.yamblzweather.model.api.data.weather.Main;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.api.data.weather.Weather;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.errors.RawToDBConvertingException;

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
                .id(weather.getId())
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

    public static DBForecast[] fromRawForecast(DBCity city, RawForecast forecast) {
        List<SingularForecast> all = forecast.getList();
        DBForecast.Builder builder = new DBForecast.Builder();

        DBForecast[] result = new DBForecast[forecast.getCnt()];
        int i = 0;
        for (SingularForecast f :
                all) {
            // todo refactor name of the class (?)
            List<tljfn.yamblzweather.model.api.data.forecast.Weather> weathers = f.getWeather();
            if (weathers == null || weathers.size() == 0) {
                throw new RawToDBConvertingException("Cannot extract weather condition from server response");
            }
            int conditionId = weathers.get(0).getId();
            int cityId = city.getOpenWeatherId();
            DBForecast single = builder.id(DBForecast.generateId(cityId, i))
                    .cityId(cityId)
                    .city(chooseDependingOnLocale(city.getRuName(), city.getEnName()))
                    .updateTime(System.currentTimeMillis())
                    .forecastTime(f.getDt())
                    .temperature(f.getMain().getTemp())
                    .condition(conditionId)
                    .build();
            result[i] = single;
            i++;
        }

        return result;
    }

    public static DBWeatherData fromRawWeatherData(DBCity city, RawWeather weather) {
        // todo rewrite more unified
        List<Weather> weathers = weather.getWeather();
        Main main = weather.getMain();

        if (weathers == null) {
            throw new RawToDBConvertingException("Cannot extract weather condition from server response");
        }
        if (main == null) {
            throw new RawToDBConvertingException("No temperature received from server response");
        }
        int condition = weathers.get(0).getId();
        double temperature = round(toCelsius(weather.getMain().getTemp()));

        DBWeatherData data = new DBWeatherData.Builder()
                .id(city.getOpenWeatherId())
                .time(System.currentTimeMillis())
                .city(chooseDependingOnLocale(city.getRuName(), city.getEnName()))
                .temperature(temperature)
                .condition(condition)
                .build();
        return data;
    }

    private static String chooseDependingOnLocale(String ru, String other) {
        // todo differentiate languages in more generic way
        return Locale.getDefault().getLanguage().equals("ru") ? ru : other;
    }
}
