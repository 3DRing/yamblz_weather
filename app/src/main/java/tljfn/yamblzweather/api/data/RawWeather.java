
package tljfn.yamblzweather.api.data;

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

}
