package tljfn.yamblzweather.repo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.db.weather.WeatherDao;

import static org.mockito.Mockito.mock;

/**
 * Created by ringov on 26.07.17.
 */
public class DatabaseRepoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    Data data;
    @Mock
    WeatherDao dao;

    DatabaseRepo repo;

    @Before
    public void setup() {
        data = new Data.Builder()
                .build();
        repo = new DatabaseRepo(dao);
    }

    @Test
    public void correctGetWeather() throws Exception {
/*        when(dao.getWeather()).thenReturn(Flowable.just(data.wm));

        repo.getWeather().test()
                .assertNoErrors()
                .assertResult(data.wm);*/
    }

    @Test
    public void errorGetWeather() throws Exception {
/*        when(dao.getWeather()).thenReturn(Flowable.error(new Throwable("test")));

        repo.getWeather().test()
                .assertErrorMessage("test");*/
    }
}