package tljfn.yamblzweather.model.repo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.CityApi;
import tljfn.yamblzweather.model.api.WeatherApi;
import tljfn.yamblzweather.model.api.data.city.RawCity;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 28.07.17.
 */
public class RemoteRepoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    WeatherApi weatherApi;
    @Mock
    CityApi cityApi;

    DataProvider dataProvider;

    RemoteRepo repo;

    @Before
    public void setup() {
        dataProvider = new DataProvider();

        when(cityApi.getAllCities()).thenReturn(Single.just(dataProvider.getAllCities()));
        repo = new RemoteRepo(weatherApi, cityApi);
    }

    @Test
    public void getting_weather_is_correct() {
        when(weatherApi.getWeather(5128581, "ru")).thenReturn(Single.fromCallable(() -> dataProvider.getNewYorkWeather()));
        repo.getWeather(5128581).test()
                .assertNoErrors()
                .assertOf(listTestObserver -> verify(weatherApi).getWeather(5128581, "ru"))
                .assertValue(dataProvider.getNewYorkWeather());
    }

    @Test
    public void getting_bad_weather_is_correct() {
        when(weatherApi.getWeather(123, "ru")).thenReturn(Single.fromCallable(() -> dataProvider.getBadWeather()));
        repo.getWeather(123).test()
                .assertNoErrors()
                .assertOf(listTestObserver -> verify(weatherApi).getWeather(123, "ru"))
                .assertValue(received -> received.equals(dataProvider.getBadWeather()));
    }

    @Test
    public void getting_error_on_weather() {
        Exception e = new Exception();
        when(weatherApi.getWeather(5128581, "ru")).thenReturn(Single.error(e));
        repo.getWeather(5128581).test()
                .assertOf(listTestObserver -> verify(weatherApi).getWeather(5128581, "ru"))
                .assertError(e);
    }

    @Test
    public void getting_cities_is_correct() {
        repo.getAllCities().test()
                .assertNoErrors()
                .assertOf(listTestObserver -> verify(cityApi).getAllCities())
                .assertValue(rawCities -> {
                    List<RawCity> toCompare = dataProvider.getAllCities();
                    return rawCities.get(0).equals(toCompare.get(0)) &&
                            rawCities.get(rawCities.size() - 1).equals(toCompare.get(toCompare.size() - 1));
                });

        verify(cityApi).getAllCities();
    }

    @Test
    public void getting_error_on_all_cities() {
        Exception e = new Exception();
        when(cityApi.getAllCities()).thenReturn(Single.fromCallable(() -> {
            throw e;
        }));

        repo.getAllCities().test()
                .assertOf(listTestObserver -> verify(cityApi).getAllCities())
                .assertError(e);
    }
}