/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tljfn.yamblzweather.db;

import android.arch.persistence.room.TypeConverter;

import tljfn.yamblzweather.vo.weather.Clouds;
import tljfn.yamblzweather.vo.weather.Coord;
import tljfn.yamblzweather.vo.weather.Main;
import tljfn.yamblzweather.vo.weather.Sys;
import tljfn.yamblzweather.vo.weather.Weather;
import tljfn.yamblzweather.vo.weather.Wind;

@SuppressWarnings("WeakerAccess") // for Room
public class WeatherTypeConverters {
    @TypeConverter
    public static Integer cloudsToInt(Clouds clouds) {
        if (clouds == null) return null;
        return Integer.parseInt(clouds.all);
    }

    @TypeConverter
    public static Clouds intToClouds(Integer integer) {
        if (integer == null) return null;
        return new Clouds(integer.toString());
    }

    @TypeConverter
    public static String coordToString(Coord coord) {
        if (coord == null) return null;
        return coord.lon + " " + coord.lat;
    }

    @TypeConverter
    public static Coord stringToCoord(String s) {
        if (s == null) return null;
        String[] strings = s.split(" ");
        return new Coord(Float.parseFloat(strings[0]), Float.parseFloat(strings[1]));
    }

    @TypeConverter
    public static String windToString(Wind wind) {
        if (wind == null) return null;
        return wind.speed + " " + wind.deg;
    }

    @TypeConverter
    public static Wind stringToWind(String s) {
        if (s == null) return null;
        String[] strings = s.split(" ");
        return new Wind(Double.parseDouble(strings[0]), Double.parseDouble(strings[1]));
    }

    @TypeConverter
    public static String sysToString(Sys sys) {
        if (sys == null) return null;
        return sys.toString();
    }

    @TypeConverter
    public static Sys stringToSys(String s) {
        if (s == null) return null;
        String[] ss = s.split(" ");
        return new Sys(Long.parseLong(ss[0]), ss[1], ss[2], Integer.parseInt(ss[3]),
                Integer.parseInt(ss[4]), Integer.parseInt(ss[5]));
    }

    @TypeConverter
    public static String weathersToString(Weather[] weathers) {
        if (weathers == null) return null;
        String answer = "";
        int length = weathers.length;
        for (int i = 0; i < length; i++) {
            answer += weathers[i].toString() + ((i == length - 1) ? "" : "|");
        }
        return answer;
    }

    @TypeConverter
    public static Weather[] stringToWeathers(String s) {
        if (s == null) return null;
        String[] weathersS = s.split("\\|");
        Weather[] weathers = new Weather[weathersS.length];
        for (int i = 0; i < weathersS.length; i++) {
            String[] ss = weathersS[i].split(" ");
            weathers[i] = new Weather(Long.parseLong(ss[0]), ss[1], ss[2], ss[3]);
        }
        return weathers;
    }

    @TypeConverter
    public static String mainToString(Main main) {
        if (main == null) return null;
        return main.toString();
    }

    @TypeConverter
    public static Main stringToMain(String s) {
        if (s == null) return null;
        String[] ss = s.split(" ");
        return new Main(ss[0], ss[1], ss[2], ss[3], ss[4]);
    }
}
