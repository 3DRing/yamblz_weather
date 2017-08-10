package tljfn.yamblzweather.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.modules.city.favorite.FavoriteCitiesInteractor;

/**
 * Created by ringov on 08.08.17.
 */

@Module
public class FavoriteCitiesInteractorModule {

    @NonNull
    @Singleton
    @Provides
    public FavoriteCitiesInteractor provideFavoriteCitiesInteractor(PreferencesRepo preferencesRepo, DatabaseRepo dbRepo) {
        return new FavoriteCitiesInteractor(preferencesRepo, dbRepo);
    }
}
