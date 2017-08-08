
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
        if (getCoord() != null ? !getCoord().equals(that.getCoord()) : that.getCoord() != null)
            return false;
        if (getWeather() != null ? !getWeather().equals(that.getWeather()) : that.getWeather() != null)
            return false;
        if (getBase() != null ? !getBase().equals(that.getBase()) : that.getBase() != null)
            return false;
        if (getMain() != null ? !getMain().equals(that.getMain()) : that.getMain() != null)
            return false;
        if (getWind() != null ? !getWind().equals(that.getWind()) : that.getWind() != null)
            return false;
        if (getClouds() != null ? !getClouds().equals(that.getClouds()) : that.getClouds() != null)
            return false;
        if (getSys() != null ? !getSys().equals(that.getSys()) : that.getSys() != null)
            return false;
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getCoord() != null ? getCoord().hashCode() : 0;
        result = 31 * result + (getWeather() != null ? getWeather().hashCode() : 0);
        result = 31 * result + (getBase() != null ? getBase().hashCode() : 0);
        result = 31 * result + (getMain() != null ? getMain().hashCode() : 0);
        result = 31 * result + getVisibility();
        result = 31 * result + (getWind() != null ? getWind().hashCode() : 0);
        result = 31 * result + (getClouds() != null ? getClouds().hashCode() : 0);
        result = 31 * result + getDt();
        result = 31 * result + (getSys() != null ? getSys().hashCode() : 0);
        result = 31 * result + getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getCod();
        return result;
    }
}
