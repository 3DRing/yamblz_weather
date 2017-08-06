package tljfn.yamblzweather.model.repo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.model.api.WeatherApi;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 28.07.17.
 */
public class RemoteRepoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    Data data;
    @Mock
    WeatherApi api;

    RemoteRepo repo;

    @Before
    public void setup() {
        data = new Data.Builder()
                .setName("Moscow")
                .build();
        repo = new RemoteRepo(api);
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