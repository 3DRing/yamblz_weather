package tljfn.yamblzweather.model.api;

import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tljfn.yamblzweather.model.api.data.forecast.RawForecast;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;

/**
 * REST API access points
 */
public interface WeatherApi {

    @GET("weather")
    Flowable<RawWeather> getWeather(@Query("id") long id);

    @GET("forecast")
    Flowable<RawForecast> getForecast(@Query("id") long id);
}
