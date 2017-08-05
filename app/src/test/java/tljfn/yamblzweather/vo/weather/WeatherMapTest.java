package tljfn.yamblzweather.vo.weather;

import org.junit.Before;
import org.junit.Test;

import tljfn.yamblzweather.Data;
import tljfn.yamblzweather.api.data.weather.RawWeather;
import tljfn.yamblzweather.api.data.weather.Clouds;
import tljfn.yamblzweather.api.data.weather.Coord;
import tljfn.yamblzweather.api.data.weather.Main;
import tljfn.yamblzweather.api.data.weather.Sys;
import tljfn.yamblzweather.api.data.weather.Weather;
import tljfn.yamblzweather.api.data.weather.Wind;

import static org.junit.Assert.*;

/**
 * Created by ringov on 26.07.17.
 */
public class WeatherMapTest {

    Data data;

    @Before
    public void setup() {
        data = new Data();

        Clouds clouds = new Clouds(data.clouds_all);
        Coord coord = new Coord(data.lon, data.lat);
        Wind wind = new Wind(data.windSpeed, data.windDeg);
        Sys sys = new Sys(data.sysId, data.sysMessage, data.sysCountry,
                data.sysType, data.sysSunset, data.sysSunrise);

        Weather weather1 = new Weather(0, "icon1", "description1", "main1");
        Weather weather2 = new Weather(1, "icon2", "description2", "main2");
        Weather[] weathers = new Weather[2];
        weathers[0] = weather1;
        weathers[1] = weather2;

        Main main = new Main("300", "200", "400", "0", "200");

        data.wm = new RawWeather(data.weathermapId, data.dt, clouds,
                coord, wind, data.cod, data.visibility,
                sys, data.name, data.base, weathers, main);
    }

    @Test
    public void fieldsAreCorrect() {
        assertTrue(data.wm.id == data.weathermapId);
        assertTrue(data.wm.dt == data.dt);
        assertTrue(data.wm.clouds.all.equals(data.clouds_all));
        assertTrue(data.wm.coord.lat == data.lat);
        assertTrue(data.wm.coord.lon == data.lon);
        assertTrue(data.wm.wind.speed == data.windSpeed);
        assertTrue(data.wm.wind.deg == data.windDeg);
        assertTrue(data.wm.cod == data.cod);
        assertTrue(data.wm.visibility == data.visibility);
        assertTrue(data.wm.sys.id == data.sysId);
        assertTrue(data.wm.sys.message.equals(data.sysMessage));
        assertTrue(data.wm.sys.country.equals(data.sysCountry));
        assertTrue(data.wm.sys.type == data.sysType);
        assertTrue(data.wm.sys.sunrise == data.sysSunrise);
        assertTrue(data.wm.sys.sunset == data.sysSunset);
        assertTrue(data.wm.name.equals(data.name));
        assertTrue(data.wm.base.equals(data.base));
        assertTrue(data.wm.weather.length == 2);
        assertTrue(data.wm.weather[0].id == 0);
        assertTrue(data.wm.weather[0].icon.equals("icon1"));
        assertTrue(data.wm.weather[0].main.equals("main1"));
        assertTrue(data.wm.weather[0].getDescription().equals("Description1"));
        assertTrue(data.wm.weather[1].id == 1);
        assertTrue(data.wm.weather[1].icon.equals("icon2"));
        assertTrue(data.wm.weather[1].main.equals("main2"));
        assertTrue(data.wm.weather[1].getDescription().equals("Description2"));
        assertTrue(data.wm.main.humidity.equals("300"));
        assertTrue(data.wm.main.pressure.equals("200"));
        assertTrue(data.wm.main.temp.equals("200"));
        assertTrue(data.wm.main.temp_min.equals("0"));
        assertTrue(data.wm.main.temp_max.equals("400"));
    }

    @Test
    public void gettersAreCorrect() {
        assertTrue(data.wm.main.getCelsius().equals("-73°"));
    }
}