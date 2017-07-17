package tljfn.yamblzweather.vo.weather;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Sys {
    public final Long id;
    public final String message;
    public final String country;
    public final Integer type;
    public final Integer sunset;
    public final Integer sunrise;

    public Sys(Long id, String message, String country, Integer type, Integer sunset, Integer sunrise) {
        this.message = message;
        this.id = id;
        this.sunset = sunset;
        this.sunrise = sunrise;
        this.type = type;
        this.country = country;
    }

    @Override
    public String toString() {
        return id.toString() + " " + message + " " + country + " " + type.toString() + " " +
                sunset.toString() + " " + sunrise.toString();
    }
}
