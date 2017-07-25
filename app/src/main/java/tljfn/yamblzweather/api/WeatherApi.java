package tljfn.yamblzweather.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tljfn.yamblzweather.vo.weather.WeatherMap;

/**
 * REST API access points
 */
public interface WeatherApi {
    @GET("weather")
    Single<WeatherMap> getWeather(@Query("q") String city,
                                  @Query("lang") String lang);

    @GET("weather")
    Single<WeatherMap> getWeather(@Query("lat") long lat,
                                  @Query("lon") long lon);
}
