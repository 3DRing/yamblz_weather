package tljfn.yamblzweather.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.repo.PreferencesRepo;

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
