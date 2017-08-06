package tljfn.yamblzweather.model.db.weather;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.api.data.weather.Weather;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 01.08.17.
 */

@Entity(tableName = "weather")
public class DBWeatherData {

    private static final double KELVIN_OFFSET = 273.15;

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
        int condition = 0;
        if (weathers != null) {
            condition = weathers.get(0).getId();
        }

        DBWeatherData data = new DBWeatherData.Builder()
                .city(weather.getName())
                .temperature(toCelsius(weather.getMain().getTemp()))
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

    @PrimaryKey
    private int id;
    private String city;
    private double temperature;
    private long time;
    private int condition;

    public DBWeatherData() {
        id = -1;
        city = "";
        temperature = 0;
        time = 0;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    static class Builder {

        DBWeatherData data;

        Builder() {
            data = new DBWeatherData();
        }

        public Builder city(String city) {
            data.city = city;
            return this;
        }

        public Builder temperature(double temperature) {
            data.temperature = temperature;
            return this;
        }

        public Builder time(long time) {
            data.time = time;
            return this;
        }

        public Builder condition(int condition) {
            data.condition = condition;
            return this;
        }

        public DBWeatherData build() {
            return data;
        }
    }
}
