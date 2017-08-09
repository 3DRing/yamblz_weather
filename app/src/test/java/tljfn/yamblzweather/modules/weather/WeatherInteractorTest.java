package tljfn.yamblzweather.modules.weather;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.db.DBConverter;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.model.repo.RemoteRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 07.08.17.
 */
public class WeatherInteractorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    RemoteRepo remoteRepo;
    @Mock
    DatabaseRepo databaseRepo;
    @Mock
    PreferencesRepo preferencesRepo;

    DataProvider dataProvider;

    WeatherInteractor interactor;

    @Before
    public void setup() {
        dataProvider = new DataProvider();
        interactor = new WeatherInteractor(remoteRepo, databaseRepo, preferencesRepo);
    }

    @Test
    public void correct_loading_of_cached_weather() {
        RawWeather raw = dataProvider.getNewYorkWeather();

        Flowable<UIWeatherData> flowable = Flowable.fromCallable(() ->
                UIConverter.toUIWeatherData(DBConverter.fromRawWeatherData(raw)));

        when(databaseRepo.loadCachedWeather()).thenReturn(flowable);

        interactor.loadCachedWeather();

        flowable.test()
                .assertNoErrors()
                .assertSubscribed()
                .assertValue(UIConverter.toUIWeatherData(
                        DBConverter.fromRawWeatherData(
                                dataProvider.getNewYorkWeather())));
    }

    @Test
    public void correct_updating_weather() {
        when(preferencesRepo.getCurrentCity()).thenReturn(Flowable.fromCallable(() -> 5128581l));

        RawWeather raw = dataProvider.getNewYorkWeather();
        when(remoteRepo.getWeather(5128581)).thenReturn(Flowable.fromCallable(() -> raw));

        Flowable<UIWeatherData> single = Flowable.fromCallable(() -> UIConverter.toUIWeatherData(
                DBConverter.fromRawWeatherData(dataProvider.getNewYorkWeather())));
        when(databaseRepo.insertOrUpdateWeather(raw)).thenReturn(single);

        interactor.updateWeather();

        single.test()
                .assertNoErrors()
                .assertValue(UIConverter.toUIWeatherData(
                        DBConverter.fromRawWeatherData(
                                dataProvider.getNewYorkWeather())))
                .assertSubscribed();
    }

}