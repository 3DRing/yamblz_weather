package tljfn.yamblzweather.api.data;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Wind {
    public final double speed;
    public final double deg;

    public Wind(double speed, double deg) {
        this.speed = speed;
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (Double.compare(wind.speed, speed) != 0) return false;
        return Double.compare(wind.deg, deg) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(speed);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(deg);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
