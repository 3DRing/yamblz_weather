package tljfn.yamblzweather.model.repo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Flowable;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.cities.CityDao;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.db.weather.WeatherDao;
import tljfn.yamblzweather.modules.UIConverter;

import static org.mockito.Mockito.when;

/**
 * Created by ringov on 26.07.17.
 */
public class DatabaseRepoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    WeatherDao weatherDao;
    @Mock
    CityDao cityDao;

    DataProvider dataProvider;

    DatabaseRepo repo;

    @Before
    public void setup() {
        repo = new DatabaseRepo(weatherDao, cityDao);
        dataProvider = new DataProvider();
    }

    @Test
    public void loading_cached_weather() {
        when(weatherDao.loadWeather()).thenReturn(Flowable.fromCallable(() ->
                DBConverter.fromRawWeatherData(dataProvider.getNewYorkWeather())));

        repo.loadCachedWeather().test()
                .assertNoErrors()
                .assertValue(cachedUIData ->
                        cachedUIData.equals(
                                UIConverter.toUIWeatherData(
                                        DBConverter.fromRawWeatherData(
                                                dataProvider.getNewYorkWeather()))));
    }
}