package tljfn.yamblzweather.model.db.weather;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import tljfn.yamblzweather.model.db.forecast.DBForecast;

/**
 * Data Access Object for the weather table.
 */
@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertWeather(DBWeatherData weather);

    @Query("SELECT * FROM weather WHERE id = :id")
    List<DBWeatherData> loadWeather(long id);

    @Query("SELECT * FROM forecast WHERE city_id = :id ORDER BY forecast_time ASC")
    List<DBForecast> getForecast(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertForecast(DBForecast[] forecasts);

}
