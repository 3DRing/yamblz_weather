package tljfn.yamblzweather.ui.start;

import tljfn.yamblzweather.vo.weather.WeatherMap;

/**
 * Created by ringov on 31.07.17.
 */

public class WeatherConverter {
    public static UIWeatherData toUIData(WeatherMap weather) {
        return new UIWeatherData();
    }
}
