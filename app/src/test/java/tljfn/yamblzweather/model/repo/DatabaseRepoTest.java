package tljfn.yamblzweather.model.repo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.cities.CityDao;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.db.weather.WeatherDao;
import tljfn.yamblzweather.model.errors.RawToDBConvertingException;
import tljfn.yamblzweather.modules.UIConverter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
        List<DBWeatherData> list = new ArrayList<>();
        list.add(DBConverter.fromRawWeatherData(dataProvider.getNewYorkWeather()));
        when(weatherDao.loadWeather(0)).thenReturn(list);

        repo.loadCachedWeather(0).test()
                .assertNoErrors()
                .assertOf(observable -> weatherDao.loadWeather(0))
                .assertValue(cachedUIData ->
                        cachedUIData.equals(DBConverter.fromRawWeatherData(dataProvider.getNewYorkWeather())));

    }

    @Test
    public void loading_cached_weather_empty() {
        when(weatherDao.loadWeather(0)).thenReturn(null);

        repo.loadCachedWeather(0).test()
                .assertNoErrors()
                .assertValue(new DBWeatherData.Builder().build())
                .assertOf(observer -> verify(weatherDao).loadWeather(0));
    }

    @Test
    public void initializing_cities_correct() {
        DBCity[] cities = dataProvider.getDBCitiesSampleArray();

        when(cityDao.addCities(cities)).thenReturn(new long[]{1l, 3l});

        repo.initCities(cities).test()
                .assertNoErrors()
                .assertOf(observer -> verify(cityDao).addCities(cities))
                .assertValue(true);
    }

    @Test
    public void initializing_cities_wrong() {
        DBCity[] cities = new DBCity[0];

        when(cityDao.addCities(cities)).thenReturn(new long[]{});

        repo.initCities(cities).test()
                .assertNoErrors()
                .assertOf(observer -> verify(cityDao).addCities(cities))
                .assertValue(false);
    }

    @Test
    public void inserting_weather_is_correct() {
        RawWeather weather = dataProvider.getNewYorkWeather();

        repo.insertOrUpdateWeather(DBConverter.fromRawWeatherData(weather)).test()
                .assertNoErrors()
                .assertOf(observer -> verify(weatherDao).insertWeather(any()))
                .assertValue(uiWeather ->
                        UIConverter.toUIWeatherData(
                                DBConverter.fromRawWeatherData(
                                        dataProvider.getNewYorkWeather())).equals(uiWeather));
    }

    @Test
    public void getting_suggestions_correct_single_variant() {
        when(cityDao.loadCitiesSuggestion("saint%")).thenReturn(Flowable.fromCallable(() -> {
            List<DBCity> cities = new ArrayList<DBCity>();
            cities.add(DBConverter.fromRawCity(dataProvider.getSaintPetersburgCity()));
            return cities;
        }));

        repo.getSuggestions("saint").test()
                .assertOf(observer -> verify(cityDao).loadCitiesSuggestion("saint%"))
                .assertNoErrors()
                .assertValue(list -> {
                    return list != null && list.size() != 0
                            && list.get(0).getEnName().equals("Saint Petersburg");
                });
    }

    @Test
    public void getting_suggestions_correct_no_variant() {
        when(cityDao.loadCitiesSuggestion("sainttt%")).thenReturn(Flowable.fromCallable(() -> {
            List<DBCity> cities = new ArrayList<DBCity>();
            return cities;
        }));

        repo.getSuggestions("sainttt").test()
                .assertOf(observer -> verify(cityDao).loadCitiesSuggestion("sainttt%"))
                .assertNoErrors()
                .assertValue(list -> list != null && list.size() == 0);
    }

    @Test
    public void getting_suggestions_unknown_error() {
        Exception e = new Exception();
        when(cityDao.loadCitiesSuggestion("sainttt%")).thenReturn(Flowable.fromCallable(() -> {
            throw e;
        }));

        repo.getSuggestions("sainttt").test()
                .assertError(e);
    }

    @Test
    public void getting_favorite_cities_correct() {
        DBCity[] sample = dataProvider.getDBCitiesSampleArray();
        when(cityDao.loadFavoriteCities(true)).thenReturn(Flowable.fromCallable(() -> {
            List<DBCity> cities = new ArrayList<DBCity>();
            cities.add(sample[0]);
            cities.add(sample[1]);
            return cities;
        }));

        repo.loadFavoriteCities().test()
                .assertNoErrors()
                .assertOf(observer -> verify(cityDao).loadFavoriteCities(true))
                .assertValue(list -> {
                    for (int i = 0; i < list.size(); i++) {
                        if (!list.get(i).equals(sample[i])) {
                            return false;
                        }
                    }
                    return true;
                });
    }

    @Test
    public void setting_favorite_city_correct() {
        DBCity[] sample = dataProvider.getDBCitiesSampleArray();
        when(cityDao.setFavorite(sample[0])).thenReturn(5);

        repo.setFavorite(sample[0]).test()
                .assertNoErrors()
                .assertOf(observer -> verify(cityDao).setFavorite(sample[0]))
                .assertValue(true);
    }
}