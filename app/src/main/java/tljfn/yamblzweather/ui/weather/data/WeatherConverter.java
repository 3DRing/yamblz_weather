package tljfn.yamblzweather.ui.weather.data;

import tljfn.yamblzweather.api.data.RawWeather;

/**
 * Created by ringov on 31.07.17.
 */

public class WeatherConverter {
    private static final double KELVIN_OFFSET = 273.15;

    public static UIWeatherData toUIData(RawWeather weather) {
        UIWeatherData uiWeather = new UIWeatherData.Builder()
                .setCity(weather.getName())
                .setTemperature(toCelsius(weather.getMain().getTemp()))
                .build();
        return uiWeather;
    }

    private static double toCelsius(double kelvins) {
        return kelvins - KELVIN_OFFSET;
    }
}
