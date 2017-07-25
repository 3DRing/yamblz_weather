package tljfn.yamblzweather.repo;

import io.reactivex.Single;
import tljfn.yamblzweather.api.PlacesApi;
import tljfn.yamblzweather.api.WeatherApi;
import tljfn.yamblzweather.vo.detailed_place_info.PlaceDetailedInfo;
import tljfn.yamblzweather.vo.weather.WeatherMap;

public class RemoteRepo {

    private final String DEFAULT_LOCALE = "ru";
    private final WeatherApi weatherApi;
    private final PlacesApi placesApi;

    public RemoteRepo(WeatherApi weatherApi, PlacesApi placesApi) {
        this.weatherApi = weatherApi;
        this.placesApi = placesApi;
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
        return weatherApi.getWeather(Math.round(lat), Math.round(lon));
    }

    public Single<WeatherMap> getWeather(long id) {
        return weatherApi.getWeather(id, DEFAULT_LOCALE);
    }

    public Single<PlaceDetailedInfo> getPlaceInfo(String id) {
        return placesApi.getPlaceInfo(id);
    }

    public Single<WeatherMap> getWeather(String zipCode, String countryCode) {
        return weatherApi.getWeather(zipCode + "," + countryCode);
    }
}