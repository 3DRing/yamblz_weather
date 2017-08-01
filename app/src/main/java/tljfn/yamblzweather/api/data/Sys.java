package tljfn.yamblzweather.api.data;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (id != sys.id) return false;
        if (type != sys.type) return false;
        if (sunset != sys.sunset) return false;
        if (sunrise != sys.sunrise) return false;
        if (!message.equals(sys.message)) return false;
        return country.equals(sys.country);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + message.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + type;
        result = 31 * result + sunset;
        result = 31 * result + sunrise;
        return result;
    }
}
