package tljfn.yamblzweather.modules;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.TestUtils;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.modules.forecast.data.RelativeTime;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;
import tljfn.yamblzweather.modules.forecast.data.UISingleForecast;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

import static org.junit.Assert.assertTrue;

/**
 * Created by ringov on 13.08.17.
 */
public class UIConverterTest {

    DataProvider dataProvider;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
    }

    @Test
    public void converting_to_ui_weather() {
        DBCity city = DBConverter.fromRawCity(dataProvider.getNewYork());
        DBWeatherData weather = dataProvider.getNewYorkWeatherDB();
        UIWeatherData weatherData = UIConverter.toUIWeatherData(city, weather);

        assertTrue(weatherData.getTemperature() == 22.22);
        assertTrue(weatherData.getCity().equals("New York"));
        assertTrue(weatherData.getConditionName() == R.string.atmospherically);
        assertTrue(weatherData.getConditionImage() == R.drawable.weather_extreme);
    }


    @Test
    public void converting_to_ui_weather_empty() {
        DBCity city = DBConverter.fromRawCity(dataProvider.getNewYork());
        UIWeatherData weatherData = UIConverter.toUIWeatherData(city, new DBWeatherData.Builder().build());

        assertTrue(weatherData.isEmpty());
    }

    @Test
    public void converting_to_single_forecast() {
        UISingleForecast forecast = UIConverter.toUISingleForecast(dataProvider.getDBForecastPetersburg()[0]);

        assertTrue(forecast.getTemperature() == 22.67);
        assertTrue(forecast.getCityName().equals("Saint Petersburg"));
        assertTrue(forecast.getCondition().equals(WeatherCondition.Rainy));
        assertTrue(forecast.getRelativeTime().getName() == R.string.evening);
    }

    @Test
    public void converting_to_ui_forecast() {
        UIForecast forecast = UIConverter.toUIForecast(Arrays.asList(dataProvider.getDBForecastPetersburg()));

        assertTrue(forecast != null);
        assertTrue(forecast.getDaysForecast() != null);
        assertTrue(TestUtils.equalTime(forecast.getTime(), System.currentTimeMillis()));

        UISingleForecast s = forecast.getDaysForecast()[1].getMorning();

        assertTrue(s.getCityName().equals("Saint Petersburg"));
        assertTrue(s.getCondition().equals(WeatherCondition.Clear));
        assertTrue(s.getTemperature() == 18.12);
        assertTrue(s.getRelativeTime().equals(RelativeTime.Morning));

        s = forecast.getDaysForecast()[3].getEvening();

        assertTrue(s.getCityName().equals("Saint Petersburg"));
        assertTrue(s.getCondition().equals(WeatherCondition.Clear));
        assertTrue(s.getTemperature() == 18.02);
        assertTrue(s.getRelativeTime().equals(RelativeTime.Evening));
    }
}