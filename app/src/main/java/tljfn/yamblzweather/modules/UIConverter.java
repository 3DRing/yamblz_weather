package tljfn.yamblzweather.modules;

import java.util.Locale;

import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.modules.city.favorite.data.FavoriteCity;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 07.08.17.
 */

public class UIConverter {

    public static UIWeatherData toUIWeatherData(DBWeatherData weather) {
        UIWeatherData data = new UIWeatherData.Builder()
                .city(weather.getCity())
                .temperature(weather.getTemperature())
                .time(weather.getTime())
                .condition(weatherIdToCondition(weather.getCondition()))
                .build();
        return data;
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


    public static CitySuggestion toUISuggestions(DBCity city) {
        StringBuilder sb = new StringBuilder();
        String name = chooseDependingOnLocale(city.getRuName(), city.getEnName());

        // todo provide correct uppercase letters for each part of city name
        name = sb.append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();
        return new CitySuggestion(city.getId(), name, city.isFavorite());
    }

    public static FavoriteCity toFavoriteCity(DBCity city) {
        return new FavoriteCity(chooseDependingOnLocale(city.getRuName(), city.getEnName()), city.isFavorite());
    }

    private static String chooseDependingOnLocale(String ru, String other) {
        // todo differentiate languages in more generic way
        return Locale.getDefault().getLanguage().equals("ru") ? ru : other;
    }

    public static UIWeatherData toUIWeatherData(DBCity city, DBWeatherData weather) {
        UIWeatherData data = new UIWeatherData.Builder()
                .city(chooseDependingOnLocale(city.getRuName(), city.getEnName()))
                .temperature(weather.getTemperature())
                .time(weather.getTime())
                .condition(weatherIdToCondition(weather.getCondition()))
                .build();
        return data;
    }
}
