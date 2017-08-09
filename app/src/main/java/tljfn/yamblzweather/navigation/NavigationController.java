/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tljfn.yamblzweather.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;

import tljfn.yamblzweather.modules.forecast.ForecastFragment;
import tljfn.yamblzweather.modules.main.MainActivity;
import tljfn.yamblzweather.modules.about.AboutFragment;
import tljfn.yamblzweather.modules.city.ChooseCityActivity;
import tljfn.yamblzweather.modules.settings.SettingsFragment;
import tljfn.yamblzweather.modules.weather.WeatherFragment;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {

    public static void navigateToAbout(@IdRes int layout, FragmentManager fragmentManager) {
        AboutFragment fragment = new AboutFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, AboutFragment.TAG)
                .commit();
    }

    public static void navigateToSettings(@IdRes int layout, FragmentManager fragmentManager) {
        SettingsFragment fragment = new SettingsFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, SettingsFragment.TAG)
                .commit();
    }

    public static void navigateToWeather(@IdRes int layout, FragmentManager fragmentManager) {
        WeatherFragment fragment = new WeatherFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, WeatherFragment.TAG)
                .commit();
    }

    public static void navigateToForecast(@IdRes int layout, FragmentManager fragmentManager) {
        ForecastFragment fragment = new ForecastFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, ForecastFragment.TAG)
                .commit();
    }

    public static void navigateToChooseCity(Context context) {
        Intent intent = new Intent(context, ChooseCityActivity.class);
        context.startActivity(intent);
    }
}
