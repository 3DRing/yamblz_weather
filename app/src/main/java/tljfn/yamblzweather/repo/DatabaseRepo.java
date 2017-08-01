package tljfn.yamblzweather.repo;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.api.data.RawWeather;
import tljfn.yamblzweather.db.DBWeatherData;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

/**
 * Using the Room database as a data source.
 */
public class DatabaseRepo {

    private final WeatherDao weatherDao;

    public DatabaseRepo(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    public Single<UIWeatherData> insertOrUpdateWeather(RawWeather weather) {
        return Single.fromCallable(() -> {
            DBWeatherData data = DBWeatherData.fromRawWeatherData(weather);
            weatherDao.insertWeather(data);
            return DBWeatherData.toUIWeatherData(data);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Deletes all from the database.
     */
    public void deleteAll() {
        //weatherDao.deleteAll();
    }

    public Flowable<UIWeatherData> loadCachedWeather() {
        return weatherDao.loadWeather()
                .map(DBWeatherData::toUIWeatherData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}