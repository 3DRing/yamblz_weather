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

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.ui.about.AboutFragment;
import tljfn.yamblzweather.ui.brand_new_settings.BrandNewSettingsFragment;
import tljfn.yamblzweather.ui.weather.WeatherFragment;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {

    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    public static void navigateToAbout(@IdRes int layout, FragmentManager fragmentManager) {
        AboutFragment fragment = new AboutFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, AboutFragment.TAG)
                .commit();
    }

    public static void navigateToSettings(@IdRes int layout, FragmentManager fragmentManager) {
        BrandNewSettingsFragment fragment = new BrandNewSettingsFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, BrandNewSettingsFragment.TAG)
                .commit();
    }

    public static void navigateToWeather(@IdRes int layout, FragmentManager fragmentManager) {
        WeatherFragment fragment = new WeatherFragment();
        fragmentManager.beginTransaction()
                .replace(layout, fragment, WeatherFragment.TAG)
                .commit();
    }

    public static void navigateToChooseCity(@IdRes int layout, FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(WeatherFragment.TAG);
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(new AutocompleteFilter.Builder()
                                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                                    //.setCountry("ru")
                                    .build())
                            .build(fragment.getActivity());
            fragment.startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            //fragment.onGooglePlacesRepairs(e.getLocalizedMessage());
        } catch (GooglePlayServicesNotAvailableException e) {
            //fragment.onGooglePlacesNotAvailable(e.getLocalizedMessage());
        }
    }

    public interface GooglePlacesExceptionCallback {
        void onGooglePlacesRepairs(String message);

        void onGooglePlacesNotAvailable(String message);
    }
}
