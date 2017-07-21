package tljfn.yamblzweather.vo.weather;

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
}
