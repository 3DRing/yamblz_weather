package tljfn.yamblzweather.modules.weather.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public class UIWeatherData extends UIBaseData {

    private long time;
    private String city;
    private double temperature;
    private WeatherCondition condition;
    private double windDeg;
    private double windSpeed;
    private double humidity;
    private double pressure;

    private UIWeatherData() {
        time = 0;
        city = "";
        temperature = 0;
        condition = WeatherCondition.Other;
    }

    public long getTime() {
        return time;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    @StringRes
    public int getConditionName() {
        return condition.getFriendlyName();
    }

    @DrawableRes
    public int getConditionImage() {
        return condition.getConditionImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UIWeatherData that = (UIWeatherData) o;

        if (Double.compare(that.getTemperature(), getTemperature()) != 0) return false;
        if (!getCity().equals(that.getCity())) return false;
        return condition == that.condition;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getCity().hashCode();
        temp = Double.doubleToLongBits(getTemperature());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + condition.hashCode();
        return result;
    }

    public static class Builder extends UIBaseData.Builder<UIWeatherData, UIWeatherData.Builder> {

        @Override
        protected UIWeatherData init() {
            return new UIWeatherData();
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder city(String city) {
            this.data.city = city;
            return this;
        }

        public Builder temperature(double temperature) {
            this.data.temperature = temperature;
            return this;
        }

        public Builder time(long time) {
            this.data.time = time;
            return this;
        }

        public Builder condition(WeatherCondition condition) {
            this.data.condition = condition;
            return this;
        }


        public Builder pressure(double pressure) {
            data.pressure = pressure;
            return this;
        }

        public Builder humidity(double humidity) {
            data.humidity = humidity;
            return this;
        }

        public Builder windDegree(double degree) {
            data.windDeg = degree;
            return this;
        }

        public Builder windSpeed(double windSpeed) {
            data.windSpeed = windSpeed;
            return this;
        }

    }
}
