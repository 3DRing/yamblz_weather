package tljfn.yamblzweather.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.modules.city.choose_city.ChooseCityInteractor;

/**
 * Created by ringov on 09.08.17.
 */

@Module
public class ChooseCityInteractorModule {

    @NonNull
    @Singleton
    @Provides
    public ChooseCityInteractor provideChooseCityInteractor(PreferencesRepo preferencesRepo, DatabaseRepo dbRepo) {
        return new ChooseCityInteractor(preferencesRepo, dbRepo);
    }

}
