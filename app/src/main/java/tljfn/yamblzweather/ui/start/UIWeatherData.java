package tljfn.yamblzweather.ui.start;

import tljfn.yamblzweather.ui.base.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public class UIWeatherData extends UIBaseData {

    private String city;
    private double temperature;

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public static class Builder {

        private UIWeatherData weather;

        public Builder() {
            weather = new UIWeatherData();
        }

        public Builder setCity(String city) {
            this.weather.city = city;
            return this;
        }

        public Builder setTemperature(double temperature) {
            this.weather.temperature = temperature;
            return this;
        }

        public UIWeatherData build() {
            return weather;
        }
    }
}
