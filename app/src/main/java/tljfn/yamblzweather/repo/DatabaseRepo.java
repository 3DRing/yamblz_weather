package tljfn.yamblzweather.repo;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import tljfn.yamblzweather.api.data.RawWeather;
import tljfn.yamblzweather.db.WeatherDao;

/**
 * Using the Room database as a data source.
 */
public class DatabaseRepo {

    private final WeatherDao weatherDao;

    public DatabaseRepo(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    /**
     * Gets the weather from the local data source.
     *
     * @return the weather from the local data source.
     */
    public Flowable<RawWeather> getWeather() {
        return weatherDao.getWeather();
    }

    /**
     * Inserts the weather in the database, or, if this is an existing weather, it updates it.
     *
     * @param weather the user to be inserted or updated.
     */
    public Completable insertOrUpdateWeather(RawWeather weather) {
        return Completable.fromAction(() -> weatherDao.insertWeather(weather));
    }

    /**
     * Deletes all from the database.
     */
    public void deleteAll() {
        weatherDao.deleteAll();
    }
}