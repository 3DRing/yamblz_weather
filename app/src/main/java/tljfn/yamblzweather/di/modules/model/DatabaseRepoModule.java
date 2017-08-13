package tljfn.yamblzweather.di.modules.model;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.db.cities.CityDao;
import tljfn.yamblzweather.model.db.weather.WeatherDao;
import tljfn.yamblzweather.model.repo.DatabaseRepo;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class DatabaseRepoModule {
    @Provides
    @NonNull
    @Singleton
    public DatabaseRepo provideDatabaseRepo(WeatherDao weatherDao, CityDao cityDao) {
        return new DatabaseRepo(weatherDao, cityDao);
    }
}
