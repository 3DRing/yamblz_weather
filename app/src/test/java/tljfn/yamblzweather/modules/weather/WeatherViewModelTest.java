package tljfn.yamblzweather.modules.weather;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.verification.VerificationMode;

import io.reactivex.Flowable;
import io.reactivex.Single;
import tljfn.yamblzweather.data.DataProvider;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment.TestLifecycleOwner;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 08.08.17.
 */
public class WeatherViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    WeatherInteractor interactor;

    DataProvider dataProvider;
    TestLifecycleOwner owner;

    WeatherViewModel viewModel;

    @Before
    public void setup() {
        owner = new TestLifecycleOwner();
        dataProvider = new DataProvider();
        when(interactor.loadCachedWeather()).thenReturn(Flowable.fromCallable(() ->
                UIConverter.toUIWeatherData(dataProvider.getNewYorkWeatherDB())));
        when(interactor.updateWeather()).thenReturn(Single.fromCallable(() ->
                UIConverter.toUIWeatherData(dataProvider.getNewYorkWeatherDB())));

        viewModel = new WeatherViewModel(interactor);
    }

    @Test
    public void load_cached_weather_correct() {
        viewModel.loadCachedWeather();
        viewModel.observe(owner, weather -> {
            verify(interactor, VerificationModeFactory.atLeastOnce()).loadCachedWeather();
            weather.equals(UIConverter
                    .toUIWeatherData(dataProvider.getNewYorkWeatherDB()));
        });
    }

    @Test
    public void update_weather_correct() {
        viewModel.updateWeather();
        viewModel.observe(owner, weather -> {
                    verify(interactor).loadCachedWeather();
                    weather.equals(UIConverter
                            .toUIWeatherData(dataProvider.getNewYorkWeatherDB()));
                }
        );
    }

    @Test
    public void building_error_data() {
        UIWeatherData data = viewModel.buildUIError("error");
        assertTrue(data.equals(new UIWeatherData.Builder().error("error").build()));
    }

}