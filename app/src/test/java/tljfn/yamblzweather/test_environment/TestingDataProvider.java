package tljfn.yamblzweather.test_environment;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.data.TestDataProvider;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by ringov on 06.08.17.
 */

public class TestingDataProvider {

    TestDataProvider dataProvider;

    @Before
    public void setup() {
        dataProvider = new TestDataProvider();
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
}
