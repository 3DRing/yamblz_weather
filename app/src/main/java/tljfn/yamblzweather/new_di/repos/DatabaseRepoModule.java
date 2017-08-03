package tljfn.yamblzweather.new_di.repos;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.repo.DatabaseRepo;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class DatabaseRepoModule {
    @Provides
    @NonNull
    @Singleton
    public DatabaseRepo provideDatabaseRepo(WeatherDao weatherDao){
        return new DatabaseRepo(weatherDao);
    }
}
