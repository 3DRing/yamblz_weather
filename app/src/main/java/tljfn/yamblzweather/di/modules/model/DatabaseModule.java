package tljfn.yamblzweather.di.modules.model;

import android.arch.persistence.room.BuildConfig;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.model.db.WeatherDatabase;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class DatabaseModule {
    @Provides
    @NonNull
    @Singleton
    public WeatherDatabase provideDatabase(Context context) {
        String dbName = context.getPackageName() + BuildConfig.BUILD_TYPE;  // is it the best possible solution for the name?
        return Room.databaseBuilder(context, WeatherDatabase.class, dbName).build();
    }
}
