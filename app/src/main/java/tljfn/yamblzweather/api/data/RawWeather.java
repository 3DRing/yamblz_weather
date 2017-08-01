package tljfn.yamblzweather.api.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import tljfn.yamblzweather.db.WeatherTypeConverters;

/**
 * Immutable model class for RawWeather
 */
@Entity(tableName = "weather")
@TypeConverters(WeatherTypeConverters.class)
public class RawWeather {

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
    public String message = "";

    public RawWeather(long id, int dt, Clouds clouds, Coord coord, Wind wind, int cod,
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

    public RawWeather updateTime() {
        this.time = new Date().getTime();
        return this;
    }

    public RawWeather setRefreshed() {
        this.isRefreshing = false;
        return this;
    }

    public String getUpdateTime() {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String str = localDateFormat.format(new Date(time));
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawWeather that = (RawWeather) o;

        if (id != that.id) return false;
        if (dt != that.dt) return false;
        if (cod != that.cod) return false;
        if (visibility != that.visibility) return false;
        if (time != that.time) return false;
        if (isRefreshing != that.isRefreshing) return false;
        if (!clouds.equals(that.clouds)) return false;
        if (!coord.equals(that.coord)) return false;
        if (!wind.equals(that.wind)) return false;
        if (!sys.equals(that.sys)) return false;
        if (!name.equals(that.name)) return false;
        if (!base.equals(that.base)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(weather, that.weather)) return false;
        return main.equals(that.main);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + dt;
        result = 31 * result + clouds.hashCode();
        result = 31 * result + coord.hashCode();
        result = 31 * result + wind.hashCode();
        result = 31 * result + cod;
        result = 31 * result + visibility;
        result = 31 * result + sys.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + base.hashCode();
        result = 31 * result + Arrays.hashCode(weather);
        result = 31 * result + main.hashCode();
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (isRefreshing ? 1 : 0);
        return result;
    }

    public void setError(String message) {
        this.message = message;
    }

    public String getError() {
        return message;
    }

    public boolean hasError() {
        return message != null && !message.isEmpty();
    }
}