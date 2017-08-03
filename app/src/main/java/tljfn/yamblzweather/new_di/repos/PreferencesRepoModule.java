package tljfn.yamblzweather.new_di.repos;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class PreferencesRepoModule {
    @Provides
    @NonNull
    @Singleton
    public PreferencesRepo providePreferencesRepo(Context context) {
        return new PreferencesRepo(context);
    }
}
