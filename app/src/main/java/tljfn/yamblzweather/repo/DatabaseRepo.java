package tljfn.yamblzweather.repo;

import io.reactivex.Flowable;
import tljfn.yamblzweather.db.Weather;
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
     * Gets the weather from the data source.
     *
     * @return the weather from the data source.
     */
    public Flowable<Weather> getWeather() {
        return weatherDao.getWeather();
    }

    /**
     * Inserts the weather in the database, or, if this is an existing weather, it updates it.
     *
     * @param weather the user to be inserted or updated.
     */
    public void insertOrUpdateWeather(Weather weather) {
        weatherDao.insertWeather(weather);
    }

    /**
     * Deletes all from the database.
     */
    public void deleteAll() {
        weatherDao.deleteAll();
    }
}