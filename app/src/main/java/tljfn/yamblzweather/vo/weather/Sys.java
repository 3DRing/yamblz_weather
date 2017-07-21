package tljfn.yamblzweather.vo.weather;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Sys {
    public final long id;
    public final String message;
    public final String country;
    public final int type;
    public final int sunset;
    public final int sunrise;

    public Sys(long id, String message, String country, int type, int sunset, int sunrise) {
        this.message = message;
        this.id = id;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.type = type;
        this.country = country;
    }

    @Override
    public String toString() {
        return id + " " + message + " " + country + " " + type + " " + sunset + " " + sunrise;
    }
}
