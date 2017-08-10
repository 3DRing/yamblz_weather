package tljfn.yamblzweather.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import tljfn.yamblzweather.model.db.cities.CityDao;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.db.weather.WeatherDao;

@Database(entities = {DBWeatherData.class, DBCity.class, DBForecast.class}, version = 6)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

    public abstract WeatherDao weatherDao();
}
