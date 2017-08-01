package tljfn.yamblzweather.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import tljfn.yamblzweather.api.data.RawWeather;

@Database(entities = {DBWeather.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
