package tljfn.yamblzweather.vo.weather;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Main {
    public final String humidity;
    public final String pressure;
    public final String temp_max;
    public final String temp_min;
    public final String temp;

    public Main(String humidity, String pressure, String temp_max, String temp_min, String temp) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.temp = temp;
    }

    @Override
    public String toString() {
        return humidity + " " + pressure + " " + temp_max + " " + temp_min + " " + temp;
    }
}
