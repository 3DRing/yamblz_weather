package tljfn.yamblzweather.repo;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Flowable;
import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.db.WeatherDao;
import tljfn.yamblzweather.vo.weather.Clouds;
import tljfn.yamblzweather.vo.weather.Coord;
import tljfn.yamblzweather.vo.weather.Main;
import tljfn.yamblzweather.vo.weather.Sys;
import tljfn.yamblzweather.vo.weather.Weather;
import tljfn.yamblzweather.vo.weather.WeatherMap;
import tljfn.yamblzweather.vo.weather.Wind;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ringov on 26.07.17.
 */
public class DatabaseRepoTest {

    Data data;
    WeatherDao dao;
    DatabaseRepo repo;

    @Before
    public void setup() {
        data = new Data.Builder()
                .build();

        dao = mock(WeatherDao.class);
        repo = new DatabaseRepo(dao);
    }

    @Test
    public void correctGetWeather() throws Exception {
        when(dao.getWeather()).thenReturn(Flowable.just(data.wm));

        repo.getWeather().test()
                .assertNoErrors()
                .assertResult(data.wm);
    }

    @Test
    public void errorGetWeather() throws Exception {
        when(dao.getWeather()).thenReturn(Flowable.error(new Throwable("test")));

        repo.getWeather().test()
                .assertErrorMessage("test");
    }
}