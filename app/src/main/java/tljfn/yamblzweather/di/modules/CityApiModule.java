package tljfn.yamblzweather.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.api.CityApi;
import tljfn.yamblzweather.model.api.CityApiImpl;

/**
 * Created by ringov on 04.08.17.
 */

@Module
public class CityApiModule {

    @NonNull
    @Provides
    @Singleton
    public CityApi provideCityApi(Context context) {
        return new CityApiImpl(context);
    }
}
