package tljfn.yamblzweather.modules;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.modules.city.UICity;
import tljfn.yamblzweather.modules.forecast.data.RelativeTime;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;
import tljfn.yamblzweather.modules.forecast.data.UISingleForecast;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 07.08.17.
 */

public class UIConverter {

    private static final int DAYS_IN_YEAR = 365; // for simplicity assume only this amount of days
    private static final int DEFAULT_DAYS_FORWARD_TO_FORECAST = 5;
    private static final double PRESSURE_FACTOR = 0.750062;

    public static UIWeatherData toUIWeatherData(DBWeatherData weather) {

        if (weather.getId() == -1) {
            return new UIWeatherData.Builder()
                    .city(weather.getCity())
                    .empty(true)
                    .build();
        } else {
            boolean ruLocale = Locale.getDefault().getLanguage().equals("ru");
            double pressure = ruLocale ? hPaToMMRtSt(weather.getPressure()) : weather.getPressure();

            UIWeatherData data = new UIWeatherData.Builder()
                    .city(weather.getCity())
                    .temperature(weather.getTemperature())
                    .time(weather.getTime())
                    .condition(weatherIdToCondition(weather.getCondition()))
                    .humidity(weather.getHumidity())
                    .pressure(pressure)
                    .windSpeed(weather.getWindSpeed())
                    .windDegree(weather.getWindDegree())
                    .build();
            return data;
        }
    }

    private static double hPaToMMRtSt(double pressure) {
        return pressure * PRESSURE_FACTOR;
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

    public static UICity toUISuggestions(DBCity city) {
        String name = chooseDependingOnLocale(city.getRuName(), city.getEnName());
        return new UICity(city.getOpenWeatherId(), name, city.getCountryCode(), city.isFavorite());
    }

    public static UICity toFavoriteCity(DBCity city) {
        return new UICity(city.getOpenWeatherId(), chooseDependingOnLocale(city.getRuName(), city.getEnName()), city.getCountryCode(), city.isFavorite());
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
                    .humidity(weather.getHumidity())
                    .pressure(weather.getPressure())
                    .windSpeed(weather.getWindSpeed())
                    .windDegree(weather.getWindDegree())
                    .build();
            return data;
        }
    }

    public static UIForecast toUIForecast(List<DBForecast> dbForecast) {
        UIForecast.Builder builder = new UIForecast.Builder(DEFAULT_DAYS_FORWARD_TO_FORECAST);

        long now = System.currentTimeMillis();

        for (int i = 0; i < dbForecast.size(); i++) {
            DBForecast crt = dbForecast.get(i);

            if (i == 0) {
                builder.time(crt.getUpdateTime());
            }

            int diff = getDaysDifference(now, crt.getForecastTime());
            if (diff < 0 || diff > DEFAULT_DAYS_FORWARD_TO_FORECAST - 1) {
                // do not take forecast from the past and far future
                continue;
            }
            RelativeTime relativeTime = toRelativeTime(crt.getForecastTime());
            switch (relativeTime) {
                case Morning:
                    builder.addMorning(diff, crt);
                    break;
                case Afternoon:
                    builder.addAfternoon(diff, crt);
                    break;
                case Evening:
                    builder.addEvening(diff, crt);
                    break;
                case Night:
                    builder.addNight(diff, crt);
                default:
                    // skip, add nothing
                    break;
            }
        }

        return builder.build();
    }

    private static int getDaysDifference(long now, long target) {
        Date nowDate = new Date(now);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(nowDate);
        Calendar c2 = Calendar.getInstance();
        Date targetDate = new Date(target);
        c2.setTime(targetDate);

        int nowDays = c1.get(Calendar.DAY_OF_YEAR);
        int targetDays = c2.get(Calendar.DAY_OF_YEAR);

        int diff = targetDays - nowDays;
        if (diff < 0 && nowDate.before(targetDate)) {
            // it means there is a difference in the year
            diff = DAYS_IN_YEAR - nowDays + targetDays; // works only for differences less then one year
        }
        return diff;
    }

    private static RelativeTime toRelativeTime(long millis) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(millis));
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 5) {
            return RelativeTime.Night;
        } else if (timeOfDay >= 5 && timeOfDay < 12) {
            return RelativeTime.Morning;
        } else if (timeOfDay >= 12 && timeOfDay < 18) {
            return RelativeTime.Afternoon;
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            return RelativeTime.Evening;
        } else {
            throw new IllegalStateException("Wrong converting of time to relative time of the day");
        }
    }

    public static UISingleForecast toUISingleForecast(DBForecast dbForecast) {
        return new UISingleForecast.Builder()
                .id(dbForecast.getId())
                .cityId(dbForecast.getCityId())
                .cityName(dbForecast.getCityName())
                .temperature(dbForecast.getTemperature())
                .updateTime(dbForecast.getUpdateTime())
                .forecastTime(dbForecast.getForecastTime())
                .relativeTime(toRelativeTime(dbForecast.getForecastTime()))
                .condition(weatherIdToCondition(dbForecast.getConditionId()))
                .build();
    }
}
