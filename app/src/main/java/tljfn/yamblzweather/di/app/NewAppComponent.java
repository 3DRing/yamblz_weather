package tljfn.yamblzweather.di.app;

import javax.inject.Singleton;

import dagger.Component;
import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.di.repos.AppModule;
import tljfn.yamblzweather.di.repos.DatabaseModule;
import tljfn.yamblzweather.di.repos.DatabaseRepoModule;
import tljfn.yamblzweather.di.repos.PreferencesRepoModule;
import tljfn.yamblzweather.di.repos.RemoteRepoModule;
import tljfn.yamblzweather.di.repos.WeatherApiModule;
import tljfn.yamblzweather.di.repos.WeatherDaoModule;
import tljfn.yamblzweather.ui.settings.SettingsFragment;
import tljfn.yamblzweather.ui.weather.WeatherViewModel;

/**
 * Created by ringov on 12.07.17.
 */

@Component(modules = {AppModule.class,
        DatabaseModule.class,
        DatabaseRepoModule.class,
        PreferencesRepoModule.class,
        RemoteRepoModule.class,
        WeatherApiModule.class,
        WeatherDaoModule.class})
@Singleton
public interface NewAppComponent {
    void inject(WeatherViewModel weatherViewModel);

    void inject(MainActivity mainActivity);

    void inject(SettingsFragment settingsFragment);
}
