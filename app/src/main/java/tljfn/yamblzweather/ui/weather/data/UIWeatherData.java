package tljfn.yamblzweather.ui.weather.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import tljfn.yamblzweather.ui.base.data.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public class UIWeatherData extends UIBaseData {

    private long time;
    private String city;
    private double temperature;
    private WeatherCondition condition;

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

    @StringRes
    public int getConditionName() {
        return condition.getFriendlyName();
    }

    @DrawableRes
    public int getConditionImage() {
        return condition.getConditionImage();
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
    }
}
