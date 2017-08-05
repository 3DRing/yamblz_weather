package tljfn.yamblzweather.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tljfn.yamblzweather.BaseFields;
import tljfn.yamblzweather.db.WeatherDatabase;

/**
 * Created by ringov on 03.08.17.
 */
@Module
public class DatabaseModule {
    @Provides
    @NonNull
    @Singleton
    public WeatherDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, WeatherDatabase.class, BaseFields.DATABASE_NAME).build();
    }
}
