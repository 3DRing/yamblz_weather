package tljfn.yamblzweather.repo;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.vo.weather.WeatherMap;

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
    public Flowable<WeatherMap> getWeather() {
        return weatherDao.getWeather();
    }

    /**
     * Inserts the weather in the database, or, if this is an existing weather, it updates it.
     *
     * @param weather the user to be inserted or updated.
     */
    public void insertOrUpdateWeather(WeatherMap weather) {
        Completable.fromAction(() -> weatherDao.insertWeather(weather))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    /**
     * Deletes all from the database.
     */
    public void deleteAll() {
        weatherDao.deleteAll();
    }
}