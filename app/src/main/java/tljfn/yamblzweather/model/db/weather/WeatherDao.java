package tljfn.yamblzweather.model.db.weather;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;

/**
 * Data Access Object for the weather table.
 */
@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(DBWeatherData weather);


    @Query("SELECT * FROM weather")
    Flowable<DBWeatherData> loadWeather();

}
