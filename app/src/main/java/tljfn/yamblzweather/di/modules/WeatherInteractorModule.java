package tljfn.yamblzweather.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.weather.WeatherInteractor;

/**
 * Created by ringov on 06.08.17.
 */

@Module
public class WeatherInteractorModule {

    @NonNull
    @Singleton
    @Provides
    public WeatherInteractor provideWeatherInteractor(RemoteRepo remoteRepo, DatabaseRepo databaseRepo, PreferencesRepo preferencesRepo) {
        return new WeatherInteractor(remoteRepo, databaseRepo, preferencesRepo);
    }
}
