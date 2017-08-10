package tljfn.yamblzweather.model.repo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.db.cities.CityDao;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.db.weather.WeatherDao;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

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

    public Flowable<UIWeatherData> insertOrUpdateWeather(DBWeatherData weather) {
        return Flowable.fromCallable(() -> {
            long result = weatherDao.insertWeather(weather);
            return UIConverter.toUIWeatherData(weather);
        });
    }

    public Flowable<DBWeatherData> loadCachedWeather(long cityId) {
        return Flowable.fromCallable(() -> {
            List<DBWeatherData> list = weatherDao.loadWeather(cityId);
            return list.size() == 0 ? new DBWeatherData.Builder().build() : list.get(0);
        });
    }

    public Flowable<List<DBCity>> getSuggestions(String requestString) {
        StringBuilder sb = new StringBuilder();
        sb.append(requestString).append("%");
        return cityDao.loadCitiesSuggestion(sb.toString());
    }

    public Single<Boolean> initCities(DBCity[] cities) {
        return Single.fromCallable(() -> {
            long[] results = cityDao.addCities(cities);
            return results.length > 0;
        });
    }

    public Flowable<List<DBCity>> loadFavoriteCities() {
        return cityDao.loadFavoriteCities(true);
    }

    public Single<Boolean> setFavorite(DBCity favorite) {
        return Single.fromCallable(() -> {
            int result = cityDao.setFavorite(favorite);
            return result > 0;
        });
    }

    public Flowable<DBCity> getCity(long cityId) {
        return cityDao.getCity(cityId);
    }
}