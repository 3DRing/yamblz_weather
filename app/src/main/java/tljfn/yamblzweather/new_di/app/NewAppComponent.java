package tljfn.yamblzweather.new_di.app;

import javax.inject.Singleton;

import dagger.Component;
import tljfn.yamblzweather.MainActivity;
import tljfn.yamblzweather.new_di.repos.AppModule;
import tljfn.yamblzweather.new_di.repos.DatabaseModule;
import tljfn.yamblzweather.new_di.repos.DatabaseRepoModule;
import tljfn.yamblzweather.new_di.repos.PreferencesRepoModule;
import tljfn.yamblzweather.new_di.repos.RemoteRepoModule;
import tljfn.yamblzweather.new_di.repos.WeatherApiModule;
import tljfn.yamblzweather.new_di.repos.WeatherDaoModule;
import tljfn.yamblzweather.ui.brand_new_settings.BrandNewSettingsFragment;
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

    void inject(BrandNewSettingsFragment settingsFragment);
}
