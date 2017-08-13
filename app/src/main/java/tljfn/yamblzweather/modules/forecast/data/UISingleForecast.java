package tljfn.yamblzweather.modules.forecast.data;

import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 10.08.17.
 */

public class UISingleForecast {

    long id;
    int cityId;
    String cityName;
    private long updateTime;
    private long forecastTime;
    private RelativeTime relativeTime;
    private double temperature;
    private WeatherCondition condition;

    public String getCityName() {
        return cityName;
    }

    public long getForecastTime() {
        return forecastTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public RelativeTime getRelativeTime() {
        return relativeTime;
    }

    public static class Builder {

        UISingleForecast forecast;

        public Builder() {
            forecast = new UISingleForecast();
        }

        public Builder fromDBForecast(DBForecast dbForecast) {
            forecast.id = dbForecast.getId();
            forecast.cityId = dbForecast.getCityId();
            forecast.updateTime = dbForecast.getUpdateTime();
            forecast.forecastTime = dbForecast.getForecastTime();
            forecast.temperature = dbForecast.getTemperature();
            forecast.cityName = dbForecast.getCityName();
            return this;
        }

        public Builder condition(WeatherCondition condition) {
            forecast.condition = condition;
            return this;
        }

        public UISingleForecast build() {
            return forecast;
        }

        public Builder id(long id) {
            forecast.id = id;
            return this;
        }

        public Builder cityId(int cityId) {
            forecast.cityId = cityId;
            return this;
        }

        public Builder cityName(String cityName) {
            forecast.cityName = cityName;
            return this;
        }

        public Builder temperature(double temperature) {
            forecast.temperature = temperature;
            return this;
        }

        public Builder updateTime(long updateTime) {
            forecast.updateTime = updateTime;
            return this;
        }

        public Builder forecastTime(long forecastTime) {
            forecast.forecastTime = forecastTime;
            return this;
        }

        public Builder relativeTime(RelativeTime relativeTime) {
            forecast.relativeTime = relativeTime;
            return this;
        }
    }
}
