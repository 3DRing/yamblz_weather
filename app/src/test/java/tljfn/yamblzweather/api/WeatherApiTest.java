package tljfn.yamblzweather.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.api.data.weather.RawWeather;

/**
 * Created by ringov on 28.07.17.
 */
public class WeatherApiTest {

    private WeatherApi api;

    private MockWebServer mockWebServer;
    private RawWeather weather;

    @Before
    public void setup() throws IOException {
        mockWebServer = new MockWebServer();
        api = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(WeatherApi.class);
        weather = new Data().getNewYorkWeatherMap();
    }

    @Test
    public void getWeatherById() throws IOException {
        enqueueResponse("weather.json");

        api.getWeather(0, "ru").test()
                .assertNoErrors()
                .assertValue(weather);
    }

    @Test
    public void getWeatherByIdBadResponse() throws IOException {
        enqueueResponse("weather_bad.json");

        // todo specify this error in the actual code
        api.getWeather(0, "ru").test()
                .assertError(Throwable.class);
    }

    @Test
    public void getWeatherByCoords() throws IOException {
        enqueueResponse("weather.json");

        api.getWeather(-74.01f, 40.71f).test()
                .assertNoErrors()
                .assertValue(weather);
    }

    @Test
    public void getWeatherByCoordsBadResponse() throws IOException {
        enqueueResponse("weather_bad.json");

        api.getWeather(-74.01f, 40.71f).test()
                .assertError(Throwable.class);
    }

    @Test
    public void getWeatherByCityName() throws IOException {
        enqueueResponse("weather.json");

        api.getWeather("New York", "ru").test()
                .assertNoErrors()
                .assertValue(weather);
    }

    @Test
    public void getWeatherByCityNameBadResponse() throws IOException {
        enqueueResponse("weather_bad.json");

        api.getWeather("New York", "ru").test()
                .assertError(Throwable.class);
    }

    @After
    public void teardown() throws IOException {
        mockWebServer.shutdown();
    }

    private void enqueueResponse(String fileName) throws IOException {
        enqueueResponse(fileName, Collections.emptyMap());
    }

    private void enqueueResponse(String fileName, Map<String, String> headers) throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("api-response/" + fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        for (Map.Entry<String, String> header : headers.entrySet()) {
            mockResponse.addHeader(header.getKey(), header.getValue());
        }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(Charset.forName("UTF-8"))));
    }
}