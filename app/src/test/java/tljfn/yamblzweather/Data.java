package tljfn.yamblzweather;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.vo.weather.Clouds;
import tljfn.yamblzweather.vo.weather.Coord;
import tljfn.yamblzweather.vo.weather.Main;
import tljfn.yamblzweather.vo.weather.Sys;
import tljfn.yamblzweather.vo.weather.Weather;
import tljfn.yamblzweather.vo.weather.WeatherMap;
import tljfn.yamblzweather.vo.weather.Wind;

/**
 * Created by ringov on 28.07.17.
 */

public class Data {
    public long weathermapId = 134744;
    public int dt = 10;
    public String clouds_all = "test clouds";
    public float lat = 213.4f;
    public float lon = 4346.4f;
    public double windSpeed = 324.4;
    public double windDeg = 43.3;
    public int sysId = 1;
    public String sysMessage = "test message";
    public String sysCountry = "ru";
    public int sysType = 5;
    public int sysSunset = 20;
    public int sysSunrise = 10;
    public int cod = 500;
    public int visibility = 1;
    public String name = "test name";
    public String base = "test base";

    public String humidity;
    public String pressure;
    public String temp_max;
    public String temp_min;
    public String temp;

    public Weather[] weathers;

    public Data() {
        Weather weather1 = new Weather(0, "icon1", "description1", "main1");
        Weather weather2 = new Weather(1, "icon2", "description2", "main2");
        weathers = new Weather[2];
        weathers[0] = weather1;
        weathers[1] = weather2;
        humidity = "300";
        pressure = "200";
        temp_max = "400";
        temp_min = "0";
        temp = "200";
    }

    public WeatherMap wm;

    public static class Builder {

        Data data;
        public List<Weather> weathers;

        public Builder() {
            data = new Data();
            weathers = new ArrayList<>();
        }

        public Builder setWeathermapId(long weathermapId) {
            data.weathermapId = weathermapId;
            return this;
        }

        public Builder setDt(int dt) {
            data.dt = dt;
            return this;
        }

        public Builder setClouds_all(String clouds_all) {
            data.clouds_all = clouds_all;
            return this;
        }

        public Builder setLat(float lat) {
            data.lat = lat;
            return this;
        }

        public Builder setLon(float lon) {
            data.lon = lon;
            return this;
        }

        public Builder setWindSpeed(double windSpeed) {
            data.windSpeed = windSpeed;
            return this;
        }

        public Builder setWindDeg(double windDeg) {
            data.windDeg = windDeg;
            return this;
        }

        public Builder setSysId(int sysId) {
            data.sysId = sysId;
            return this;
        }

        public Builder setSysMessage(String sysMessage) {
            data.sysMessage = sysMessage;
            return this;
        }

        public Builder setSysCountry(String sysCountry) {
            data.sysCountry = sysCountry;
            return this;
        }

        public Builder setSysType(int sysType) {
            data.sysType = sysType;
            return this;
        }

        public Builder setSysSunset(int sysSunset) {
            data.sysSunset = sysSunset;
            return this;
        }

        public Builder setSysSunrise(int sysSunrise) {
            data.sysSunrise = sysSunrise;
            return this;
        }

        public Builder setCod(int cod) {
            data.cod = cod;
            return this;
        }

        public Builder setVisibility(int visibility) {
            data.visibility = visibility;
            return this;
        }

        public Builder setName(String name) {
            data.name = name;
            return this;
        }

        public Builder setBase(String base) {
            data.base = base;
            return this;
        }

        public Builder addWeather(Weather weather) {
            weathers.add(weather);
            return this;
        }

        public Builder setHumidity(String humidity) {
            data.humidity = humidity;
            return this;
        }

        public Builder setPressure(String pressure) {
            data.pressure = pressure;
            return this;
        }

        public Builder setTemp_max(String temp_max) {
            data.temp_max = temp_max;
            return this;
        }

        public Builder setTemp_min(String temp_min) {
            data.temp_min = temp_min;
            return this;
        }

        public Builder setTemp(String temp) {
            data.temp = temp;
            return this;
        }


        public Data build() {
            Clouds clouds = new Clouds(data.clouds_all);
            Coord coord = new Coord(data.lon, data.lat);
            Wind wind = new Wind(data.windSpeed, data.windDeg);
            Sys sys = new Sys(data.sysId, data.sysMessage, data.sysCountry,
                    data.sysType, data.sysSunset, data.sysSunrise);

            Main main = new Main(data.humidity, data.pressure, data.temp_max, data.temp_min, data.temp);

            data.wm = new WeatherMap(data.weathermapId, data.dt, clouds,
                    coord, wind, data.cod, data.visibility,
                    sys, data.name, data.base, weathers.toArray(new Weather[weathers.size()]), main);
            return data;
        }
    }

    public WeatherMap getNewYorkWeatherMap() {
        return new Builder()
                .setLon(-74.01f)
                .setLat(40.71f)
                .addWeather(new Weather(721, "50d", "haze", "Haze"))
                .addWeather(new Weather(701, "50d", "mist", "Mist"))
                .addWeather(new Weather(741, "50d", "fog", "Fog"))
                .setBase("stations")
                .setTemp("295.37")
                .setPressure("1012")
                .setHumidity("83")
                .setTemp_min("292.15")
                .setTemp_max("298.15")
                .setVisibility(12874)
                .setWindSpeed(4.1)
                .setWindDeg(30)
                .setClouds_all("75")
                .setDt(1501244760)
                .setSysType(1)
                .setSysId(2119)
                .setSysMessage("0.0028")
                .setSysCountry("US")
                .setSysSunrise(1501235381)
                .setSysSunset(1501287287)
                .setWeathermapId(5128581)
                .setName("New York")
                .setCod(200)
                .build().wm;
    }
}
