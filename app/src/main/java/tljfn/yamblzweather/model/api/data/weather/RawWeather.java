
package tljfn.yamblzweather.model.api.data.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RawWeather {

    @SerializedName("coord")
    Coord coord;
    @SerializedName("weather")
    List<Weather> weather = null;
    @SerializedName("base")
    String base;
    @SerializedName("main")
    Main main;
    @SerializedName("visibility")
    int visibility;
    @SerializedName("wind")
    Wind wind;
    @SerializedName("clouds")
    Clouds clouds;
    @SerializedName("dt")
    int dt;
    @SerializedName("sys")
    Sys sys;
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("cod")
    int cod;

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawWeather that = (RawWeather) o;

        if (getVisibility() != that.getVisibility()) return false;
        if (getDt() != that.getDt()) return false;
        if (getId() != that.getId()) return false;
        if (getCod() != that.getCod()) return false;
        if (!getCoord().equals(that.getCoord())) return false;
        if (!getWeather().equals(that.getWeather())) return false;
        if (!getBase().equals(that.getBase())) return false;
        if (!getMain().equals(that.getMain())) return false;
        if (!getWind().equals(that.getWind())) return false;
        if (!getClouds().equals(that.getClouds())) return false;
        if (!getSys().equals(that.getSys())) return false;
        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        int result = getCoord().hashCode();
        result = 31 * result + getWeather().hashCode();
        result = 31 * result + getBase().hashCode();
        result = 31 * result + getMain().hashCode();
        result = 31 * result + getVisibility();
        result = 31 * result + getWind().hashCode();
        result = 31 * result + getClouds().hashCode();
        result = 31 * result + getDt();
        result = 31 * result + getSys().hashCode();
        result = 31 * result + getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getCod();
        return result;
    }
}
