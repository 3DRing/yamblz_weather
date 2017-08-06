package tljfn.yamblzweather.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.api.CityApi;
import tljfn.yamblzweather.model.api.WeatherApi;
import tljfn.yamblzweather.model.repo.RemoteRepo;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class RemoteRepoModule {
    @Provides
    @NonNull
    @Singleton
    public RemoteRepo provideRemoteRepo(WeatherApi weatherApi, CityApi cityApi) {
        return new RemoteRepo(weatherApi, cityApi);
    }
}
