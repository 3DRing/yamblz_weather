package tljfn.yamblzweather.vo.weather;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Weather {
    public final Long id;
    public final String icon;
    public final String description;
    public final String main;

    public Weather(Long id, String icon, String description, String main) {
        this.id = id;
        this.icon = icon;
        this.description = description;
        this.main = main;
    }

    @Override
    public String toString() {
        return id.toString() + " " + icon + " " + description + " " + main;
    }
}
