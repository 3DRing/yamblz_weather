package tljfn.yamblzweather.repo;

import io.reactivex.Single;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.vo.weather.WeatherMap;

public class RemoteRepo {

    private final String DEFAULT_LOCALE = "ru";
    private final WeatherApi weatherApi;

    public RemoteRepo(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    /**
     * Gets the weather from the remote data source.
     *
     * @return the weather from the remote data source.
     */
    @Deprecated
    public Single<WeatherMap> getWeather(String city) {
        return weatherApi.getWeather(city, DEFAULT_LOCALE);
    }

    public Single<WeatherMap> getWeather(double lat, double lon) {
        return weatherApi.getWeather(lat, lon);
    }

    public Single<WeatherMap> getWeather(long id) {
        return weatherApi.getWeather(id, DEFAULT_LOCALE);
    }
}