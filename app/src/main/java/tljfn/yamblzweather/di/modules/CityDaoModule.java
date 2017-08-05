package tljfn.yamblzweather.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.db.WeatherDatabase;
import tljfn.yamblzweather.db.cities.CityDao;

/**
 * Created by ringov on 04.08.17.
 */
@Module
public class CityDaoModule {

    @NonNull
    @Provides
    @Singleton
    public CityDao provideCityDao(WeatherDatabase database) {
        return database.cityDao();
    }

}
