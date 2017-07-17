package tljfn.yamblzweather.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tljfn.yamblzweather.vo.weather.WeatherMap;

/**
 * REST API access points
 */
public interface WeatherApi {
    //todo change to interceptor
    @GET("weather?q={city}&appid=f66b70ebb071127760c387562b9308c8&lang=ru")
    Single<WeatherMap> getWeather(@Path("city") String city);
}
