package tljfn.yamblzweather.repo;

import io.reactivex.Single;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.vo.weather.WeatherMap;

public class RemoteRepo {

    private final WeatherApi weatherApi;

    public RemoteRepo(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    /**
     * Gets the weather from the remote data source.
     *
     * @return the weather from the remote data source.
     */
    public Single<WeatherMap> getWeather(String city) {
        return weatherApi.getWeather(city, "ru");
    }
}