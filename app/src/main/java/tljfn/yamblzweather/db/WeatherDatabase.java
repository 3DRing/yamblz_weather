package tljfn.yamblzweather.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import tljfn.yamblzweather.vo.weather.WeatherMap;

/**
 * The Room database that contains the WeatherMap table
 */
@Database(entities = {WeatherMap.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
