package tljfn.yamblzweather.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tljfn.yamblzweather.ui.main.MainActivity;
import tljfn.yamblzweather.di.modules.AppModule;
import tljfn.yamblzweather.di.modules.CityApiModule;
import tljfn.yamblzweather.di.modules.CityDaoModule;
import tljfn.yamblzweather.di.modules.DatabaseModule;
import tljfn.yamblzweather.di.modules.DatabaseRepoModule;
import tljfn.yamblzweather.di.modules.PreferencesRepoModule;
import tljfn.yamblzweather.di.modules.RemoteRepoModule;
import tljfn.yamblzweather.di.modules.WeatherApiModule;
import tljfn.yamblzweather.di.modules.WeatherDaoModule;
import tljfn.yamblzweather.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.ui.choose_city.ChooseCityViewModel;
import tljfn.yamblzweather.ui.main.MainViewModel;
import tljfn.yamblzweather.ui.settings.SettingsFragment;
import tljfn.yamblzweather.ui.weather.WeatherViewModel;

/**
 * Created by ringov on 12.07.17.
 */

@Component(modules = {
        AppModule.class,
        CityApiModule.class,
        CityDaoModule.class,
        DatabaseModule.class,
        DatabaseRepoModule.class,
        PreferencesRepoModule.class,
        RemoteRepoModule.class,
        WeatherApiModule.class,
        WeatherDaoModule.class})
@Singleton
public interface NewAppComponent {
    void inject(WeatherViewModel weatherViewModel);

    void inject(SettingsFragment settingsFragment);

    void inject(ChooseCityViewModel chooseCityViewModel);

    void inject(WeatherUpdateJob weatherUpdateJob);

    void inject(MainViewModel mainViewModel);
}
