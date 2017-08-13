package tljfn.yamblzweather.di.modules.weather;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.forecast.ForecastInteractor;

/**
 * Created by ringov on 09.08.17.
 */

@Module
public class ForecastInteractorModule {

    @NonNull
    @Provides
    @Singleton
    public ForecastInteractor provideForecastInteractor(PreferencesRepo preferencesRepo, RemoteRepo remoteRepo, DatabaseRepo dbRepo) {
        return new ForecastInteractor(preferencesRepo, remoteRepo, dbRepo);
    }

}