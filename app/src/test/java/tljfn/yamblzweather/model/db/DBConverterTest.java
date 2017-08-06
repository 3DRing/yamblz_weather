package tljfn.yamblzweather.model.db;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.model.errors.RawToDBConvertingException;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created by ringov on 06.08.17.
 */

public class DBConverterTest {

    DataProvider dataProvider;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
    }

    @Test
    public void converting_to_db_weather_data_correctly() {
        RawWeather rawData = dataProvider.getNewYorkWeather();
        DBWeatherData dbData = DBConverter.fromRawWeatherData(rawData);
        long crtTime = System.currentTimeMillis();

        // not used so far
        //assertTrue(dbData.getId() == 5128581);

        assertTrue(dbData.getCity().equals("New York"));
        assertTrue(dbData.getCondition() == 721);
        assertTrue(Double.compare(dbData.getTemperature(), 22.22) == 0);
        assertTrue(equalTime(dbData.getTime(), crtTime));
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_weather_data_error() {
        RawWeather data = dataProvider.getBadWeather();
        DBWeatherData dbWeatherData = DBConverter.fromRawWeatherData(data);
        fail();
    }

    @Test
    public void converting_to_db_city_correctly() {
        RawCity data = dataProvider.getSaintPetersburgCity();
        DBCity dbData = DBConverter.fromRawCity(data);

        assertTrue(dbData != null);
        assertTrue(dbData.getId() == 2);
        assertTrue(dbData.getOpenWeatherId() == 498817);
        assertTrue(dbData.getCountryCode().equals("ru"));
        assertTrue(dbData.getEnName().equals("saint petersburg"));
        assertTrue(dbData.getRuName().equals("санкт-петербург"));
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error() {
        RawCity data = dataProvider.getBadRawCity();
        DBCity dbData = DBConverter.fromRawCity(data);
        fail();
    }

    private boolean equalTime(long time1, long time2) {
        return Math.abs(time1 - time2) < 1000; // diff less then 1 second
    }
}

