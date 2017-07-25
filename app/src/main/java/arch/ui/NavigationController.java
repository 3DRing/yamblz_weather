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

package arch.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;

import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.about.AboutFragment;
import tljfn.yamblzweather.ui.city_search.ChooseCityFragment;
import tljfn.yamblzweather.ui.settings.SettingsFragment;
import tljfn.yamblzweather.ui.start.StartFragment;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.fragment_container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToAbout() {
        AboutFragment fragment = new AboutFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToSettings() {
        SettingsFragment fragment = new SettingsFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToStart() {
        StartFragment fragment = new StartFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, StartFragment.TAG)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public void navigateToChooseCity() {
        Fragment fragment = fragmentManager.findFragmentByTag(StartFragment.TAG);
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

    /**
     * This method pushes fragment to screen.
     * Your fragment must have static method "getFragmentTag()"
     *
     * @param fragmentClass Class of the fragment you wanna to push
     * @deprecated Use {@link #navigateToStart()} or similar
     */
    @Deprecated
    public void pushFragment(Class<? extends BaseFragment> fragmentClass) {
        try {
            Method method;
            method = fragmentClass.getDeclaredMethod("getFragmentTag");
            String tag = method != null ? method.invoke(null).toString() : null;
            if (fragmentManager.findFragmentByTag(tag) == null) {
                // Wow, fragmentManager doesn`t have necessary object of fragment, so we have to
                // create new one. On this stage we store maximum 2 fragment in backstack.
                int backStackEntryCount = fragmentManager.getBackStackEntryCount();
                while (backStackEntryCount > 1) {
                    fragmentManager.popBackStack();
                    backStackEntryCount--;
                }
                BaseFragment fragment = fragmentClass.newInstance();
                fragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .replace(R.id.fragment_container, fragment, tag)
                        .addToBackStack(tag)
                        .commit();
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException
                | java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }

    public interface GooglePlacesExceptionCallback {
        void onGooglePlacesRepairs(String message);

        void onGooglePlacesNotAvailable(String message);
    }
}
