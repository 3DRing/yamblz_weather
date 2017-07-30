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

    public String getCelsius() {
        return String.valueOf(Math.round(Float.parseFloat(temp) - 273.15)) + "°";
    }

    @Override
    public String toString() {
        return humidity + " " + pressure + " " + temp_max + " " + temp_min + " " + temp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (!humidity.equals(main.humidity)) return false;
        if (!pressure.equals(main.pressure)) return false;
        if (!temp_max.equals(main.temp_max)) return false;
        if (!temp_min.equals(main.temp_min)) return false;
        return temp.equals(main.temp);

    }

    @Override
    public int hashCode() {
        int result = humidity.hashCode();
        result = 31 * result + pressure.hashCode();
        result = 31 * result + temp_max.hashCode();
        result = 31 * result + temp_min.hashCode();
        result = 31 * result + temp.hashCode();
        return result;
    }
}
