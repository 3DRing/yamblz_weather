package tljfn.yamblzweather.ui.start;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.repo.RemoteRepo;
import tljfn.yamblzweather.vo.weather.WeatherMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 30.07.17.
 */
public class WeatherViewModelTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    RemoteRepo remote;
    @Mock
    PreferencesRepo prefs;
    @Mock
    DatabaseRepo db;

    WeatherViewModel vm;

    WeatherMap cachedData;
    WeatherMap remoteData;

    long crtCity;

    @Before
    public void setup() {
        crtCity = 5128581;
        cachedData = new Data.Builder().build().wm;
        remoteData = new Data.Builder().build().getNewYorkWeatherMap();

        when(db.getWeather()).thenReturn(Flowable.just(cachedData));
        when(db.insertOrUpdateWeather(remoteData)).thenReturn(Completable.complete());
        when(remote.getWeather(crtCity)).thenReturn(Single.just(remoteData));
        when(prefs.getCurrentCity()).thenReturn(Single.just(crtCity));
        when(remote.getWeather(40.71, -74.01)).thenReturn(Single.just(remoteData));

        vm = new WeatherViewModel(remote, db, prefs);
    }

    @Test
    public void getCache() {
        vm.getWeather().test()
                .assertNoErrors()
                .assertValue(new Data.Builder().build().wm);
    }

    @Test
    public void getCacheException() throws Exception {
        Exception e = new Exception();
        when(db.getWeather()).thenReturn(Flowable.error(e));
        vm.getWeather().test()
                .assertError(e);
    }

    @Test
    public void updateWeather() {
        vm.updateWeather();
        verify(prefs).getCurrentCity();
    }

    @Test
    public void changeCity() {
        vm.changeCity(40.71, -74.01);
        verify(remote).getWeather(40.71, -74.01);
    }
}