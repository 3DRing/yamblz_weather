package tljfn.yamblzweather.db.weather;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import tljfn.yamblzweather.api.data.weather.RawWeather;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

/**
 * Created by ringov on 01.08.17.
 */

@Entity(tableName = "weather")
public class DBWeatherData {

    private static final double KELVIN_OFFSET = 273.15;

    private static double toCelsius(double kelvins) {
        return kelvins - KELVIN_OFFSET;
    }

    public static DBWeatherData fromRawWeatherData(RawWeather weather) {
        DBWeatherData data = new DBWeatherData.Builder()
                .city(weather.getName())
                .temperature(toCelsius(weather.getMain().getTemp()))
                .build();
        return data;
    }

    public static UIWeatherData toUIWeatherData(DBWeatherData weather) {
        UIWeatherData data = new UIWeatherData.Builder()
                .setCity(weather.getCity())
                .setTemperature(weather.getTemperature())
                .build();
        return data;
    }

    @PrimaryKey
    private int id;
    private String city;
    private double temperature;

    public DBWeatherData() {
        id = -1;
        city = "";
        temperature = 0;
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

        public DBWeatherData build() {
            return data;
        }
    }
}
