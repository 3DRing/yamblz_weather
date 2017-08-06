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

    @PrimaryKey
    private int id;
    // todo use city id
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
