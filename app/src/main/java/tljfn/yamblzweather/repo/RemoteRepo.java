package tljfn.yamblzweather.repo;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.api.data.RawWeather;

public class RemoteRepo {

    static final String DEFAULT_LOCALE = "ru";

    private final WeatherApi weatherApi;

    public RemoteRepo(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
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
}