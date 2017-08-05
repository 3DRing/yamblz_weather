package tljfn.yamblzweather.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @NonNull
    @Singleton
    public Context provideContext() {
        return context;
    }
}
