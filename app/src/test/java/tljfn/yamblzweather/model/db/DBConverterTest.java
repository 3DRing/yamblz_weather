package tljfn.yamblzweather.model.db;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.db.weather.DBWeatherConverter;
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
    public void converting_to_db_data_correctly() {
        RawWeather rawData = dataProvider.getNewYorkWeather();
        DBWeatherData dbData = DBWeatherConverter.fromRawWeatherData(rawData);
        long crtTime = System.currentTimeMillis();

        // not used so far
        //assertTrue(dbData.getId() == 5128581);

        assertTrue(dbData.getCity().equals("New York"));
        assertTrue(dbData.getCondition() == 721);
        assertTrue(Double.compare(dbData.getTemperature(), 22.22) == 0);
        assertTrue(equalTime(dbData.getTime(), crtTime));
    }

    @Test(expected = RawToDBConvertingException.class)
    public void converting_to_db_data_error() {
        RawWeather data = dataProvider.getBadWeather();
        DBWeatherData dbWeatherData = DBWeatherConverter.fromRawWeatherData(data);
        fail();
    }

    private boolean equalTime(long time1, long time2) {
        return Math.abs(time1 - time2) < 1000; // diff less then 1 second
    }
}

