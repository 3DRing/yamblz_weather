package tljfn.yamblzweather.model.api;

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
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;

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
        weather = new DataProvider().getNewYorkWeather();
    }

    @Test
    public void get_weather_by_id() throws IOException {
        enqueueResponse("new_york_weather.json");

        api.getWeather(0, "ru").test()
                .assertNoErrors()
                .assertValue(rawWeather -> rawWeather.equals(weather));
    }

    @Test
    public void getting_weather_by_id_with_bad_response() throws IOException {
        enqueueResponse("bad_weather_response.json");

        // todo specify this error in the actual code
        api.getWeather(0, "ru").test()
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