package tljfn.yamblzweather.api.data;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Weather {
    public final long id;
    public final String icon;
    public final String main;
    private final String description;

    public Weather(long id, String icon, String description, String main) {
        this.id = id;
        this.icon = icon;
        this.description = description;
        this.main = main;
    }

    public String getDescription() {
        return description.substring(0, 1).toUpperCase() + description.substring(1);
    }

    @Override
    public String toString() {
        return id + " " + icon + " " + description + " " + main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (id != weather.id) return false;
        if (!icon.equals(weather.icon)) return false;
        if (!main.equals(weather.main)) return false;
        return getDescription().equals(weather.getDescription());

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + icon.hashCode();
        result = 31 * result + main.hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
