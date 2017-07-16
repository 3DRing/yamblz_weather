package tljfn.yamblzweather.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tljfn.yamblzweather.db.Weather;

/**
 * REST API access points
 */
public interface WeatherService {
    //todo change to interceptor
    @GET("weather?q={city}&appid=f66b70ebb071127760c387562b9308c8&lang=ru")
    Single<Weather> getWeather(@Path("city") String city);
}
