package tljfn.yamblzweather.vo.weather;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tljfn.yamblzweather.db.WeatherTypeConverters;

/**
 * Immutable model class for WeatherMap
 */
@Entity(tableName = "weather")
@TypeConverters(WeatherTypeConverters.class)
public class WeatherMap {

    @PrimaryKey
    public final long id;
    public final int dt;
    public final Clouds clouds;
    public final Coord coord;
    public final Wind wind;
    public final int cod;
    public final int visibility;
    public final Sys sys;
    public final String name;
    public final String base;
    public final Weather[] weather;
    public final Main main;
    public long time;
    public boolean isRefreshing;

    public WeatherMap(long id, int dt, Clouds clouds, Coord coord, Wind wind, int cod,
                      int visibility, Sys sys, String name, String base, Weather[] weather, Main main) {
        this.id = id;
        this.dt = dt;
        this.clouds = clouds;
        this.coord = coord;
        this.wind = wind;
        this.cod = cod;
        this.visibility = visibility;
        this.sys = sys;
        this.name = name;
        this.base = base;
        this.weather = weather;
        this.main = main;
    }

    public WeatherMap updateTime() {
        this.time = new Date().getTime();
        return this;
    }

    public WeatherMap setRefreshed() {
        this.isRefreshing = false;
        return this;
    }

    public String getUpdateTime() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return localDateFormat.format(new Date(time));
    }
}