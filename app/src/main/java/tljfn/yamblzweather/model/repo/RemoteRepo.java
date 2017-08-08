package tljfn.yamblzweather.model.repo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.api.CityApi;
import tljfn.yamblzweather.model.api.WeatherApi;
import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;

public class RemoteRepo {

    static final String DEFAULT_LOCALE = "ru";

    private final WeatherApi weatherApi;
    private final CityApi cityApi;

    @Inject
    public RemoteRepo(WeatherApi weatherApi, CityApi cityApi) {
        this.weatherApi = weatherApi;
        this.cityApi = cityApi;
    }

    public Single<RawWeather> getWeather(long id) {
        return weatherApi.getWeather(id, DEFAULT_LOCALE);
    }

    public Single<List<RawCity>> getAllCities(){
        return cityApi.getAllCities();
    }
}