package tljfn.yamblzweather.new_di.repos;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.repo.RemoteRepo;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class RemoteRepoModule {
    @Provides
    @NonNull
    @Singleton
    public RemoteRepo provideRemoteRepo(WeatherApi weatherApi) {
        return new RemoteRepo(weatherApi);
    }
}
