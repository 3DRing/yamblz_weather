package tljfn.yamblzweather.di.component;

import javax.inject.Singleton;

import dagger.Component;
import tljfn.yamblzweather.di.modules.AppModule;
import tljfn.yamblzweather.di.modules.ChooseCityInteractorModule;
import tljfn.yamblzweather.di.modules.CityApiModule;
import tljfn.yamblzweather.di.modules.CityDaoModule;
import tljfn.yamblzweather.di.modules.DatabaseModule;
import tljfn.yamblzweather.di.modules.DatabaseRepoModule;
import tljfn.yamblzweather.di.modules.PreferencesRepoModule;
import tljfn.yamblzweather.di.modules.RemoteRepoModule;
import tljfn.yamblzweather.di.modules.SharedPreferencesModule;
import tljfn.yamblzweather.di.modules.ViewModelModule;
import tljfn.yamblzweather.di.modules.WeatherApiModule;
import tljfn.yamblzweather.di.modules.WeatherDaoModule;
import tljfn.yamblzweather.di.modules.WeatherInteractorModule;
import tljfn.yamblzweather.model.scheduler.WeatherUpdateJob;
import tljfn.yamblzweather.modules.about.AboutFragment;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityFragment;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityViewModel;
import tljfn.yamblzweather.modules.city.favorite.FavoriteCitiesFragment;
import tljfn.yamblzweather.modules.main.MainViewModel;
import tljfn.yamblzweather.modules.settings.SettingsFragment;
import tljfn.yamblzweather.modules.weather.WeatherFragment;
import tljfn.yamblzweather.modules.weather.WeatherViewModel;

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

    void inject(SettingsFragment settingsFragment);

    void inject(WeatherUpdateJob weatherUpdateJob);

    void inject(MainViewModel mainViewModel);

    void inject(AboutFragment aboutFragment);

    void inject(ChooseCityFragment chooseCityFragment);

    void inject(WeatherFragment weatherFragment);

    void inject(FavoriteCitiesFragment favoriteCitiesFragment);
}
