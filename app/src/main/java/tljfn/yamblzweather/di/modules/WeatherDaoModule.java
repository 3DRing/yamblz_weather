package tljfn.yamblzweather.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.db.weather.WeatherDao;
import tljfn.yamblzweather.db.WeatherDatabase;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class WeatherDaoModule {
    @Singleton
    @Provides
    WeatherDao provideWeatherDao(WeatherDatabase database) {
        return database.weatherDao();
    }
}