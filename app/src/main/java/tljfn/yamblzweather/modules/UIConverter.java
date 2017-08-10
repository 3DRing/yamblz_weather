package tljfn.yamblzweather.modules;

import java.util.Locale;

import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.modules.city.UICity;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;
import tljfn.yamblzweather.modules.forecast.data.UISingleForecast;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 07.08.17.
 */

public class UIConverter {

    public static UIWeatherData toUIWeatherData(DBWeatherData weather) {

        if (weather.getId() == -1) {
            return new UIWeatherData.Builder()
                    .city(weather.getCity())
                    .empty(true)
                    .build();
        } else {
            UIWeatherData data = new UIWeatherData.Builder()
                    .city(weather.getCity())
                    .temperature(weather.getTemperature())
                    .time(weather.getTime())
                    .condition(weatherIdToCondition(weather.getCondition()))
                    .build();
            return data;
        }
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

    public static UIForecast toUIForecast(DBCity city, DBForecast dbForecast) {
        return new UIForecast();
    }

    public static UICity toUISuggestions(DBCity city) {
        StringBuilder sb = new StringBuilder();
        String name = chooseDependingOnLocale(city.getRuName(), city.getEnName());

        // todo provide correct uppercase letters for each part of city name
        name = sb.append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();
        return new UICity(city.getOpenWeatherId(), name, city.isFavorite());
    }

    public static UICity toFavoriteCity(DBCity city) {
        return new UICity(city.getOpenWeatherId(), chooseDependingOnLocale(city.getRuName(), city.getEnName()), city.isFavorite());
    }

    private static String chooseDependingOnLocale(String ru, String other) {
        // todo differentiate languages in more generic way
        return Locale.getDefault().getLanguage().equals("ru") ? ru : other;
    }

    public static UIWeatherData toUIWeatherData(DBCity city, DBWeatherData weather) {
        if (weather.getId() == -1) {
            return new UIWeatherData.Builder()
                    .city(chooseDependingOnLocale(city.getRuName(), city.getEnName()))
                    .empty(true)
                    .build();
        } else {
            UIWeatherData data = new UIWeatherData.Builder()
                    .city(chooseDependingOnLocale(city.getRuName(), city.getEnName()))
                    .temperature(weather.getTemperature())
                    .time(weather.getTime())
                    .condition(weatherIdToCondition(weather.getCondition()))
                    .build();
            return data;
        }
    }

    public static UIForecast toUIForecast(DBForecast dbForecast) {
        return new UIForecast();
    }

    public static UISingleForecast toUISingleForecast(DBForecast dbForecast) {
        return new UISingleForecast.Builder()
                .fromDBForecast(dbForecast)
                .condition(weatherIdToCondition(dbForecast.getConditionId()))
                .build();
    }
}
