package tljfn.yamblzweather.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tljfn.yamblzweather.di.modules.AppModule;
import tljfn.yamblzweather.di.modules.city.ChooseCityInteractorModule;
import tljfn.yamblzweather.di.modules.city.CityApiModule;
import tljfn.yamblzweather.di.modules.city.CityDaoModule;
import tljfn.yamblzweather.di.modules.model.DatabaseModule;
import tljfn.yamblzweather.di.modules.model.DatabaseRepoModule;
import tljfn.yamblzweather.di.modules.model.PreferencesRepoModule;
import tljfn.yamblzweather.di.modules.model.RemoteRepoModule;
import tljfn.yamblzweather.di.modules.model.SharedPreferencesModule;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelModule;
import tljfn.yamblzweather.di.modules.weather.WeatherApiModule;
import tljfn.yamblzweather.di.modules.weather.WeatherDaoModule;
import tljfn.yamblzweather.di.modules.weather.WeatherInteractorModule;
import tljfn.yamblzweather.model.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.modules.about.AboutFragment;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityFragment;
import tljfn.yamblzweather.modules.city.favorite.FavoriteCitiesFragment;
import tljfn.yamblzweather.modules.forecast.ForecastFragment;
import tljfn.yamblzweather.modules.main.MainActivity;
import tljfn.yamblzweather.modules.settings.SettingsFragment;
import tljfn.yamblzweather.modules.weather.WeatherFragment;

/**
 * Created by ringov on 12.07.17.
 */

@Component(modules = {
        AppModule.class,
        ChooseCityInteractorModule.class,
        CityApiModule.class,
        CityDaoModule.class,
        DatabaseModule.class,
        DatabaseRepoModule.class,
        PreferencesRepoModule.class,
        RemoteRepoModule.class,
        SharedPreferencesModule.class,
        ViewModelModule.class,
        WeatherApiModule.class,
        WeatherDaoModule.class,
        WeatherInteractorModule.class})
@Singleton
public interface NewAppComponent {

    void inject(SettingsFragment fragment);

    void inject(WeatherUpdateJob weatherUpdateJob);

    void inject(AboutFragment fragment);

    void inject(ChooseCityFragment fragment);

    void inject(WeatherFragment fragment);

    void inject(FavoriteCitiesFragment fragment);

    void inject(ForecastFragment fragment);

    void inject(MainActivity mainActivity);
}
