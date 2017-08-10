package tljfn.yamblzweather.model.db.forecast;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ringov on 10.08.17.
 */

@Entity(tableName = "forecast")
public class DBForecast {

    public static long generateId(int cityId, int count) {
        return cityId * 1000 + count; // unique id dependant on the city id and with unique part at 3 lower registers
    }

    @PrimaryKey
    long id;
    int cityId;
    String cityName;
    long updateTime;
    long forecastTime;
    double temperature;
    int conditionId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(long forecastTime) {
        this.forecastTime = forecastTime;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public static class Builder {

        DBForecast forecast;

        public Builder() {
            forecast = new DBForecast();
        }

        public Builder id(long id) {
            this.forecast.id = id;
            return this;
        }

        public Builder cityId(int cityId) {
            this.forecast.cityId = cityId;
            return this;
        }

        public Builder city(String name) {
            this.forecast.cityName = name;
            return this;
        }

        public Builder updateTime(long time) {
            this.forecast.updateTime = time;
            return this;
        }

        public Builder forecastTime(long time) {
            this.forecast.forecastTime = time;
            return this;
        }

        public Builder temperature(double temperature) {
            this.forecast.temperature = temperature;
            return this;
        }

        public Builder condition(int conditionId) {
            this.forecast.conditionId = conditionId;
            return this;
        }

        public DBForecast build() {
            return forecast;
        }
    }
}
