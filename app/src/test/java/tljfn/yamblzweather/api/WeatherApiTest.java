package tljfn.yamblzweather.api;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.vo.weather.Weather;
import tljfn.yamblzweather.vo.weather.WeatherMap;

import static org.junit.Assert.*;

/**
 * Created by ringov on 28.07.17.
 */
public class WeatherApiTest {

    private WeatherApi api;

    private MockWebServer mockWebServer;
    private WeatherMap weather;

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
    public void getWeather() throws IOException {
        enqueueResponse("weather.json");

        api.getWeather(0, "ru").test()
                .assertNoErrors()
                .assertValue(weather);
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
                .setBody(source.readString(StandardCharsets.UTF_8)));
    }
}