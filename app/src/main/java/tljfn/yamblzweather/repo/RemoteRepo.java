package tljfn.yamblzweather.repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.api.CityApi;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.api.data.city.RawCity;
import tljfn.yamblzweather.api.data.weather.RawWeather;

public class RemoteRepo {

    static final String DEFAULT_LOCALE = "ru";

    private final WeatherApi weatherApi;
    private final CityApi cityApi;

    @Inject
    public RemoteRepo(WeatherApi weatherApi, CityApi cityApi) {
        this.weatherApi = weatherApi;
        this.cityApi = cityApi;
    }

    public Single<RawWeather> getWeather(double lat, double lon) {
        return weatherApi.getWeather(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<RawWeather> getWeather(long id) {
        return weatherApi.getWeather(id, DEFAULT_LOCALE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<RawCity>> getAllCities(){
        return cityApi.getAllCities();
    }
}