package tljfn.yamblzweather.modules.weather.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import tljfn.yamblzweather.R;

/**
 * Created by ringov on 06.08.17.
 */

public enum WeatherCondition {
    Cloudy(R.string.cloudy, R.drawable.weather_clouds),
    Clear(R.string.clear, R.drawable.weather_clear),
    Rainy(R.string.rainy, R.drawable.weather_rainy),
    Thunderstorm(R.string.thunderstorm, R.drawable.weather_storm),
    Snow(R.string.snow, R.drawable.weather_snow),
    Atmospherically(R.string.atmospherically, R.drawable.weather_extreme),
    Extreme(R.string.extreme, R.drawable.weather_extreme),
    Windy(R.string.windy, R.drawable.weather_wind),
    Calm(R.string.calm, R.drawable.weather_clear),
    Other(R.string.other, R.drawable.weather_other);

    @StringRes
    private final int friendlyName;
    @DrawableRes
    private final int image;

    WeatherCondition(@StringRes int friendlyName, @DrawableRes int conditionImage) {
        this.friendlyName = friendlyName;
        this.image = conditionImage;
    }

    @StringRes
    public int getFriendlyName() {
        return friendlyName;
    }

    @DrawableRes
    public int getConditionImage() {
        return image;
    }
}
