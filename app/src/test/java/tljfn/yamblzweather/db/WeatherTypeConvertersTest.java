package tljfn.yamblzweather.db;

import org.junit.Test;

import tljfn.yamblzweather.api.data.Clouds;
import tljfn.yamblzweather.api.data.Coord;
import tljfn.yamblzweather.api.data.Main;
import tljfn.yamblzweather.api.data.Sys;
import tljfn.yamblzweather.api.data.Weather;
import tljfn.yamblzweather.api.data.Wind;

import static org.junit.Assert.*;

/**
 * Created by ringov on 26.07.17.
 */
public class WeatherTypeConvertersTest {
    @Test
    public void cloudsToInt() {
        Clouds clouds = new Clouds("45");
        Integer result = WeatherTypeConverters.cloudsToInt(clouds);
        assertTrue(result == 45);
        result = WeatherTypeConverters.cloudsToInt(null);
        assertTrue(result == null);
    }

    @Test(expected = NumberFormatException.class)
    public void cloudsToIntException() {
        Clouds clouds = new Clouds("exception");
        WeatherTypeConverters.cloudsToInt(clouds);
    }

    @Test
    public void intToClouds() {
        Clouds clouds = WeatherTypeConverters.intToClouds(60);
        assertTrue(clouds.all.equals("60"));
    }

    @Test
    public void coordToString() {
        Coord coord = new Coord(123.4f, 55.1f);
        String result = WeatherTypeConverters.coordToString(coord);
        assertTrue(result.equals("123.4 55.1"));

        result = WeatherTypeConverters.coordToString(null);
        assertTrue(result == null);
    }

    @Test
    public void stringToCoord() {
        Coord coord = WeatherTypeConverters.stringToCoord("33.1 7484.3");
        assertTrue(coord.lon == 33.1f);
        assertTrue(coord.lat == 7484.3f);
    }

    @Test(expected = NumberFormatException.class)
    public void stringToCoordException() {
        WeatherTypeConverters.stringToCoord("exception");
    }

    @Test
    public void windToString() {
        Wind wind = new Wind(1000.3, 5667.4);
        String result = WeatherTypeConverters.windToString(wind);
        assertTrue(result.equals("1000.3 5667.4"));

        result = WeatherTypeConverters.windToString(null);
        assertTrue(result == null);
    }

    @Test
    public void stringToWind() {
        Wind wind = WeatherTypeConverters.stringToWind("33.8 87.5");
        assertTrue(wind.speed == 33.8);
        assertTrue(wind.deg == 87.5);
    }

    @Test(expected = NumberFormatException.class)
    public void stringToWindException() {
        WeatherTypeConverters.stringToWind("exception");
    }

    @Test
    public void sysToString() {
        Sys sys = new Sys(0, "message", "ru", 6, 20, 10);
        String result = WeatherTypeConverters.sysToString(sys);
        assertTrue(result.equals("0 message ru 6 20 10"));

        result = WeatherTypeConverters.sysToString(null);
        assertTrue(result == null);
    }

    @Test
    public void stringToSys() {
        Sys sys = WeatherTypeConverters.stringToSys("0 message ru 6 20 10");
        assertTrue(sys.id == 0);
        assertTrue(sys.message.equals("message"));
        assertTrue(sys.country.equals("ru"));
        assertTrue(sys.type == 6);
        assertTrue(sys.sunset == 20);
        assertTrue(sys.sunrise == 10);
    }

    @Test(expected = NumberFormatException.class)
    public void stringToSysException() {
        WeatherTypeConverters.stringToSys("exception");
    }

    @Test
    public void weathersToString() {
        Weather weather1 = new Weather(0, "icon1", "description1", "main1");
        Weather weather2 = new Weather(1, "icon2", "description2", "main2");
        Weather[] weathers = new Weather[2];
        weathers[0] = weather1;
        weathers[1] = weather2;

        String result = WeatherTypeConverters.weathersToString(weathers);
        assertTrue(result.equals("0 icon1 description1 main1|1 icon2 description2 main2"));

        result = WeatherTypeConverters.weathersToString(null);
        assertTrue(result == null);
    }

    @Test
    public void stringToWeathers() {
        Weather[] weather = WeatherTypeConverters.stringToWeathers("0 icon1 description1 main1|1 icon2 description2 main2");
        assertTrue(weather.length == 2);
        assertTrue(weather[0].toString().equals("0 icon1 description1 main1"));
        assertTrue(weather[1].toString().equals("1 icon2 description2 main2"));
    }

    @Test
    public void mainToString() {
        Main main = new Main("10", "11", "12", "13", "14");
        String result = WeatherTypeConverters.mainToString(main);
        assertTrue(result.equals("10 11 12 13 14"));

        result = WeatherTypeConverters.mainToString(null);
        assertTrue(result == null);
    }

    @Test
    public void stringToMain() {
        Main main = WeatherTypeConverters.stringToMain("10 11 12 13 14");
        assertTrue(main.humidity.equals("10"));
        assertTrue(main.pressure.equals("11"));
        assertTrue(main.temp_max.equals("12"));
        assertTrue(main.temp_min.equals("13"));
        assertTrue(main.temp.equals("14"));
    }

}