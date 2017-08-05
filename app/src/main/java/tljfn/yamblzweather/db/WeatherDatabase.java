package tljfn.yamblzweather.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import tljfn.yamblzweather.db.cities.CityDao;
import tljfn.yamblzweather.db.cities.DBCity;
import tljfn.yamblzweather.db.weather.DBWeatherData;
import tljfn.yamblzweather.db.weather.WeatherDao;

@Database(entities = {DBWeatherData.class, DBCity.class}, version = 3)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

    public abstract WeatherDao weatherDao();
}
