package tljfn.yamblzweather.modules;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.TestUtils;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.db.weather.DBWeatherData;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestion;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Created by ringov on 07.08.17.
 */
public class UIConverterTest {

    DataProvider dataProvider;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
    }

    @Test
    public void converting_to_ui_weather() {
        DBWeatherData weather = dataProvider.getNewYorkWeatherDB();
        UIWeatherData uiWeather = UIConverter.toUIWeatherData(weather);

        long crtTime = System.currentTimeMillis();
        assertTrue(TestUtils.equalTime(crtTime, uiWeather.getTime()));

        assertTrue(uiWeather.getCity().equals("New York"));
        assertTrue(uiWeather.getTemperature() == 22.22);
        assertTrue(uiWeather.getConditionName() == WeatherCondition.Atmospherically.getFriendlyName());
        assertTrue(uiWeather.getConditionImage() == WeatherCondition.Atmospherically.getConditionImage());
    }

    @Test
    public void converting_to_ui_suggestions() {
        DBCity city = dataProvider.getSaintPetersburgCityDB();
        UICitySuggestion suggestion = UIConverter.toUISuggestions(city);

        // todo fix uppercase for all words
        assertTrue(suggestion.getName().equals("Saint petersburg"));
    }

}