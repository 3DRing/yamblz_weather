package tljfn.yamblzweather.navigation;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.about.AboutFragment;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityFragment;
import tljfn.yamblzweather.modules.city.favorite.FavoriteCitiesFragment;
import tljfn.yamblzweather.modules.forecast.ForecastFragment;
import tljfn.yamblzweather.modules.settings.SettingsFragment;
import tljfn.yamblzweather.modules.weather.WeatherFragment;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 13.08.17.
 */
public class NavigationControllerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    FragmentManager fm;
    @Mock
    FragmentTransaction transaction;
    @Mock
    Context context;

    @IdRes
    int container;

    @Before
    public void setup() {
        container = R.id.fragment_container;
    }

    @Test
    public void navigate_to_about_screen() {
        testNavigateToFragment(container, AboutFragment.class, AboutFragment.TAG,
                NavigationController::navigateToAbout);
    }

    @Test
    public void do_not_navigate_to_about_screen_if_opened() {
        testNotNavigateToFragment(container, new AboutFragment(), AboutFragment.TAG,
                NavigationController::navigateToAbout);
    }

    @Test
    public void navigate_to_weather_screen() {
        testNavigateToFragment(container, WeatherFragment.class, WeatherFragment.TAG,
                NavigationController::navigateToWeather);
    }

    @Test
    public void do_not_navigate_to_weather_if_opened() {
        testNotNavigateToFragment(container, new WeatherFragment(), WeatherFragment.TAG,
                NavigationController::navigateToWeather);
    }

    @Test
    public void navigate_to_forecast_screen() {
        testNavigateToFragment(container, ForecastFragment.class, ForecastFragment.TAG,
                NavigationController::navigateToForecast);
    }

    @Test
    public void do_not_navigate_to_forecast_if_opened() {
        testNotNavigateToFragment(container, new ForecastFragment(), ForecastFragment.TAG,
                NavigationController::navigateToForecast);
    }

    @Test
    public void navigate_to_choose_city_screen() {
        testNavigateToFragment(container, ChooseCityFragment.class, ChooseCityFragment.TAG,
                NavigationController::navigateToChooseCity);
    }

    @Test
    public void do_not_navigate_to_choose_city_if_opened() {
        testNotNavigateToFragment(container, new ChooseCityFragment(), ChooseCityFragment.TAG,
                NavigationController::navigateToChooseCity);
    }

    @Test
    public void navigate_to_favorite_city_screen() {
        testNavigateToFragment(container, FavoriteCitiesFragment.class, FavoriteCitiesFragment.TAG,
                NavigationController::navigateToFavoriteCity);
    }

    @Test
    public void do_not_navigate_to_favorite_city_if_opened() {
        testNotNavigateToFragment(container, new FavoriteCitiesFragment(), FavoriteCitiesFragment.TAG,
                NavigationController::navigateToFavoriteCity);
    }

    @Test
    public void navigate_to_settings_screen() {
        testNavigateToFragment(container, SettingsFragment.class, SettingsFragment.TAG,
                NavigationController::navigateToSettings);
    }

    @Test
    public void do_not_navigate_to_settings_if_opened() {
        testNotNavigateToFragment(container, new SettingsFragment(), SettingsFragment.TAG,
                NavigationController::navigateToSettings);
    }

    private void testNotNavigateToFragment(@IdRes int container, Fragment fragment, String tag, NavigatorFunction nf) {
        when(fm.findFragmentByTag(tag)).thenReturn(fragment);
        nf.navigate(container, fm);
        verify(fm).findFragmentByTag(tag);
        verify(fm, never()).beginTransaction();
    }

    private void testNavigateToFragment(@IdRes int container, Class<? extends Fragment> fCls, String tag, NavigatorFunction nf) {
        when(fm.findFragmentByTag(tag)).thenReturn(null);
        when(fm.beginTransaction()).thenReturn(transaction);
        when(transaction.replace(eq(container), any(fCls), eq(tag))).thenReturn(transaction);

        nf.navigate(container, fm);

        verify(fm).findFragmentByTag(tag);
        verify(fm).beginTransaction();
        verify(transaction).replace(eq(container), any(fCls), eq(tag));
        verify(transaction).commit();
    }

    private interface NavigatorFunction {
        void navigate(@IdRes int container, FragmentManager fm);
    }
}