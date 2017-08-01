package tljfn.yamblzweather.ui.weather.data;

import tljfn.yamblzweather.ui.base.data.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public class UIWeatherData extends UIBaseData {

    private String city;
    private double temperature;

    private UIWeatherData() {
        super();
        city = "";
        temperature = 0;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
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

        public Builder setCity(String city) {
            this.data.city = city;
            return this;
        }

        public Builder setTemperature(double temperature) {
            this.data.temperature = temperature;
            return this;
        }
    }
}
