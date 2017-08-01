package tljfn.yamblzweather.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;
import tljfn.yamblzweather.api.data.RawWeather;

/**
 * Data Access Object for the weather table.
 */
@Dao
public interface WeatherDao {

    /**
     * Get the weather from the table.
     *
     * @return the weather from the table
     */
    @Query("SELECT * FROM weather")
    Flowable<RawWeather> getWeather();

    /**
     * Insert a weather in the database. If the weather already exists, replace it.
     *
     * @param weatherMap the weather to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(RawWeather weatherMap);

    /**
     * Delete all.
     */
    @Query("DELETE FROM weather")
    void deleteAll();
}
