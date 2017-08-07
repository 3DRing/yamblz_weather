package tljfn.yamblzweather.test_environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by ringov on 06.08.17.
 */

public class TestingDataProvider {

    DataProvider dataProvider;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
    }

    @Test
    public void loading_weather_without_errors() {
        try {
            RawWeather newYork = dataProvider.getNewYorkWeather();
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void loading_notnull_newyork_weather() {
        RawWeather newYork = dataProvider.getNewYorkWeather();

        assertTrue(newYork.getBase() != null);
        assertTrue(newYork.getName() != null);
        assertTrue(newYork.getClouds() != null);
        assertTrue(newYork.getCod() == 200);
        assertTrue(newYork.getId() == 5128581);
        assertTrue(newYork.getCoord() != null);
        assertTrue(newYork.getMain() != null);
        assertTrue(newYork.getSys() != null);
        assertTrue(newYork.getWeather() != null);
        assertTrue(newYork.getWind() != null);
    }

    @Test
    public void loading_bad_weather_without_errors() {
        try {
            RawWeather badWeather = dataProvider.getBadWeather();
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void loading_bad_weather_with_nulls() {
        RawWeather badWeather = dataProvider.getBadWeather();

        assertTrue(badWeather.getBase() == null);
        assertTrue(badWeather.getName().equals(""));
        assertTrue(badWeather.getClouds() == null);
        assertTrue(badWeather.getCod() == -1);
        assertTrue(badWeather.getCoord() == null);
        assertTrue(badWeather.getMain() == null);
        assertTrue(badWeather.getSys() == null);
        assertTrue(badWeather.getWeather() == null);
        assertTrue(badWeather.getWind() == null);
    }

    @Test
    public void loading_cities_input_stream_is_correct() {
        InputStream is = dataProvider.getCitiesInputStream();
        InputStreamReader isr = new InputStreamReader(is);

        Gson gson = new GsonBuilder().create();
        Type itemsListType = new TypeToken<List<RawCity>>() {
        }.getType();
        List<RawCity> cities = gson.fromJson(isr, itemsListType);

        assertTrue(cities != null);
        assertTrue(cities.size() != 0);

        RawCity raw = cities.get(0);

        assertTrue(raw.yaId == 2);
        assertTrue(raw.openWeatherId == 498817);
        assertTrue(raw.country.equals("ru"));
        assertTrue(raw.enName.equals("Saint Petersburg"));
        assertTrue(raw.ruName.equals("Санкт-Петербург"));
    }

    @Test
    public void loading_raw_city_correctly() {
        RawCity city = dataProvider.getSaintPetersburgCity();

        assertTrue(city != null);
        assertTrue(city.yaId == 2);
        assertTrue(city.openWeatherId == 498817);
        assertTrue(city.country.equals("ru"));
        assertTrue(city.enName.equals("Saint Petersburg"));
        assertTrue(city.ruName.equals("Санкт-Петербург"));
    }

    @Test
    public void loading_raw_city_with_nulls() {
        RawCity city = dataProvider.getBadRawCity();

        assertTrue(city != null);
        assertTrue(city.yaId == -1);
        assertTrue(city.openWeatherId == 0);
        assertTrue(city.country == null);
        assertTrue(city.enName == null);
        assertTrue(city.ruName == null);
    }

    @Test
    public void loading_all_raw_cities_correct() {
        List<RawCity> allCities = dataProvider.getAllCities();

        assertTrue(allCities != null);
        assertTrue(allCities.size() != 0);

        RawCity raw = allCities.get(0);

        assertTrue(raw.yaId == 2);
        assertTrue(raw.openWeatherId == 498817);
        assertTrue(raw.country.equals("ru"));
        assertTrue(raw.enName.equals("Saint Petersburg"));
        assertTrue(raw.ruName.equals("Санкт-Петербург"));
    }
}
