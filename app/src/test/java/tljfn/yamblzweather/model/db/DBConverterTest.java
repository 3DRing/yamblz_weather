package tljfn.yamblzweather.model.db;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.TestUtils;
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
        assertTrue(TestUtils.equalTime(dbData.getTime(), crtTime));
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
        assertTrue(dbData.getEnName().equals("Saint Petersburg"));
        assertTrue(dbData.getRuName().equals("Санкт-Петербург"));
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error() {
        RawCity data = dataProvider.getBadRawCity();
        DBCity dbData = DBConverter.fromRawCity(data);
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error_2() {
        RawCity data = new RawCity();
        DBCity dbData = DBConverter.fromRawCity(data);
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error_3() {
        RawCity data = new RawCity();
        data.yaId = 1;
        DBCity dbData = DBConverter.fromRawCity(data);
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error_4() {
        RawCity data = new RawCity();
        data.openWeatherId = 1;
        DBCity dbData = DBConverter.fromRawCity(data);
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error_5() {
        RawCity data = new RawCity();
        data.country = "ru";
        DBCity dbData = DBConverter.fromRawCity(data);
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error_6() {
        RawCity data = new RawCity();
        data.enName = "Rome";
        DBCity dbData = DBConverter.fromRawCity(data);
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_city_error_7() {
        RawCity data = new RawCity();
        data.ruName = "Рим";
        DBCity dbData = DBConverter.fromRawCity(data);
    }
}

