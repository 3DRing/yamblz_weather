
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingularForecast {

    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;
    @SerializedName("rain")
    @Expose
    private Rain rain;

    public long getDt() {
        return dt * 1000;   // server gives dt in seconds from 1970
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingularForecast that = (SingularForecast) o;

        if (getDt() != that.getDt()) return false;
        if (getMain() != null ? !getMain().equals(that.getMain()) : that.getMain() != null)
            return false;
        if (getWeather() != null ? !getWeather().equals(that.getWeather()) : that.getWeather() != null)
            return false;
        if (getClouds() != null ? !getClouds().equals(that.getClouds()) : that.getClouds() != null)
            return false;
        if (getWind() != null ? !getWind().equals(that.getWind()) : that.getWind() != null)
            return false;
        if (getSys() != null ? !getSys().equals(that.getSys()) : that.getSys() != null)
            return false;
        if (getDtTxt() != null ? !getDtTxt().equals(that.getDtTxt()) : that.getDtTxt() != null)
            return false;
        return getRain() != null ? getRain().equals(that.getRain()) : that.getRain() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getDt() ^ (getDt() >>> 32));
        result = 31 * result + (getMain() != null ? getMain().hashCode() : 0);
        result = 31 * result + (getWeather() != null ? getWeather().hashCode() : 0);
        result = 31 * result + (getClouds() != null ? getClouds().hashCode() : 0);
        result = 31 * result + (getWind() != null ? getWind().hashCode() : 0);
        result = 31 * result + (getSys() != null ? getSys().hashCode() : 0);
        result = 31 * result + (getDtTxt() != null ? getDtTxt().hashCode() : 0);
        result = 31 * result + (getRain() != null ? getRain().hashCode() : 0);
        return result;
    }
}
