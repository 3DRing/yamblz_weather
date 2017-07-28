package tljfn.yamblzweather.repo;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.api.WeatherApi;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 28.07.17.
 */
public class RemoteRepoTest {

    Data data;
    WeatherApi api;
    RemoteRepo repo;

    @Before
    public void setup() {
        data = new Data.Builder()
                .setName("Moscow")
                .build();
        api = mock(WeatherApi.class);
        repo = new RemoteRepo(api);
    }

    @Test
    public void getWeatherByCity() {
        when(api.getWeather("Moscow", RemoteRepo.DEFAULT_LOCALE)).thenReturn(Single.just(data.wm));

        repo.getWeather("Moscow").test()
                .assertNoErrors()
                .assertValue(data.wm);
    }

    @Test
    public void getWeatherByCoords() {
        Data data = new Data.Builder()
                .setName("Somecity")
                .setLat(56.5f)
                .setLon(22.31f)
                .build();
        when(api.getWeather(56.5, 22.31)).thenReturn(Single.just(data.wm));

        repo.getWeather(56.5, 22.31).test()
                .assertNoErrors()
                .assertValue(data.wm);
    }

    @Test
    public void getWeatherById() {
        Data data = new Data.Builder()
                .setName("Somecity")
                .setWeathermapId(8)
                .build();
        when(api.getWeather(8, RemoteRepo.DEFAULT_LOCALE)).thenReturn(Single.just(data.wm));
        repo.getWeather(8).test()
                .assertNoErrors()
                .assertValue(data.wm);
    }

}