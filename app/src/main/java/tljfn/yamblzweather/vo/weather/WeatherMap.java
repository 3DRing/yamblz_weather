package tljfn.yamblzweather.vo.weather;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import tljfn.yamblzweather.db.WeatherTypeConverters;

/**
 * Immutable model class for WeatherMap
 */
@Entity(tableName = "weather")
@TypeConverters(WeatherTypeConverters.class)
public class WeatherMap {

    @PrimaryKey
    public final Long id;
    public final Integer dt;
    public final Clouds clouds;
    public final Coord coord;
    public final Wind wind;
    public final Integer cod;
    public final Integer visibility;
    public final Sys sys;
    public final String name;
    public final String base;
    public final Weather[] weather;
    public final Main main;

    public WeatherMap(Long id, Integer dt, Clouds clouds, Coord coord, Wind wind, Integer cod,
                      Integer visibility, Sys sys, String name, String base, Weather[] weather, Main main) {
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
}