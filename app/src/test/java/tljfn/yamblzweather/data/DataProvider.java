package tljfn.yamblzweather.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;

/**
 * Created by ringov on 06.08.17.
 */

public class DataProvider {

    private <T> T loadTestData(String fileName, Class<T> cls) {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("api-response/" + fileName);
        InputStreamReader isr = new InputStreamReader(inputStream);

        Gson gson = new GsonBuilder().create();

        return gson.fromJson(isr, cls);
    }

    private RawWeather loadTestRawWeather(String fileName) {
        return loadTestData(fileName, RawWeather.class);
    }

    private RawCity loadTestRawCity(String fileName) {
        return loadTestData(fileName, RawCity.class);
    }

    public RawWeather getNewYorkWeather() {
        return loadTestRawWeather("new_york_weather.json");
    }

    public RawWeather getBadWeather() {
        return loadTestRawWeather("bad_weather_object.json");
    }

    public InputStream getCitiesInputStream() {
        return getClass().getClassLoader()
                .getResourceAsStream("api-response/" + "cities.json");
    }

    public RawCity getSaintPetersburgCity() {
        return loadTestRawCity("saint_petersburg_city.json");
    }

    public RawCity getBadRawCity() {
        return loadTestRawCity("bad_city_response.json");
    }
}
