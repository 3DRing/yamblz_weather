
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    double speed;
    @SerializedName("deg")
    double deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        if (Double.compare(wind.getSpeed(), getSpeed()) != 0) return false;
        return Double.compare(wind.getDeg(), getDeg()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getSpeed());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getDeg());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
