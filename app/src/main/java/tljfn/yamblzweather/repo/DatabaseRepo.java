package tljfn.yamblzweather.repo;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiPredicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import tljfn.yamblzweather.api.data.weather.RawWeather;
import tljfn.yamblzweather.db.cities.CityDao;
import tljfn.yamblzweather.db.cities.DBCity;
import tljfn.yamblzweather.db.weather.DBWeatherData;
import tljfn.yamblzweather.db.weather.WeatherDao;
import tljfn.yamblzweather.ui.choose_city.data.UICitySuggestion;
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

/**
 * Using the Room database as a data source.
 */
public class DatabaseRepo {

    private final WeatherDao weatherDao;
    private final CityDao cityDao;

    public DatabaseRepo(WeatherDao weatherDao, CityDao cityDao) {
        this.weatherDao = weatherDao;
        this.cityDao = cityDao;
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

    public Flowable<List<UICitySuggestion>> getSuggestions(String requestString) {
        StringBuilder sb = new StringBuilder();
        sb.append(requestString).append("%");
        return cityDao.loadCitiesSuggestion(sb.toString())
                .flatMap(cities -> Flowable.fromIterable(cities)
                        .map(DBCity::toUISuggestions)
                        .toSortedList((city1, city2) -> city1.getName().compareTo(city2.getName()))
                        .toFlowable())
                .flatMapSingle(Single::just)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Boolean> initCities(DBCity[] cities) {
        return Single.fromCallable(() -> {
            long[] results = cityDao.addCities(cities);
            return results.length > 0;
        });
    }
}