
package tljfn.yamblzweather.model.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    double lon;
    @SerializedName("lat")
    double lat;

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (Double.compare(coord.getLon(), getLon()) != 0) return false;
        return Double.compare(coord.getLat(), getLat()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getLon());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLat());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
