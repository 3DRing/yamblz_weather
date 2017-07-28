package tljfn.yamblzweather;

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

    public WeatherMap wm;

    public static class Builder {

        Data data;

        public Builder() {
            data = new Data();
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

        public Data build() {
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

            data.wm = new WeatherMap(data.weathermapId, data.dt, clouds,
                    coord, wind, data.cod, data.visibility,
                    sys, data.name, data.base, weathers, main);
            return data;
        }
    }
}
