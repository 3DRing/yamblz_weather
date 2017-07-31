package tljfn.yamblzweather.ui.start;

import tljfn.yamblzweather.vo.weather.WeatherMap;

/**
 * Created by ringov on 31.07.17.
 */

public class WeatherConverter {
    private static final double KELVIN_OFFSET = 273.15;

    public static UIWeatherData toUIData(WeatherMap weather) {
        UIWeatherData uiWeather = new UIWeatherData.Builder()
                .setCity(weather.name)
                .setTemperature(toCelsius(Double.parseDouble(weather.main.temp)))
                .build();
        return uiWeather;
    }

    private static double toCelsius(double kelvins) {
        return kelvins - KELVIN_OFFSET;
    }
}
