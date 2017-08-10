package tljfn.yamblzweather.modules.weather.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public class UIWeatherData extends UIBaseData {

    private long time;
    private String city;
    private double temperature;
    private WeatherCondition condition;
    private boolean empty;

    private UIWeatherData() {
        time = 0;
        city = "";
        temperature = 0;
        condition = WeatherCondition.Other;
        empty = false;
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

    public boolean isEmpty() {
        return empty;
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

        public Builder empty(boolean empty) {
            this.data.empty = empty;
            return this;
        }
    }
}
