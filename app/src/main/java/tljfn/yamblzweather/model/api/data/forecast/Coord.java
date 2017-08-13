
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lon")
    @Expose
    private double lon;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (Double.compare(coord.getLat(), getLat()) != 0) return false;
        return Double.compare(coord.getLon(), getLon()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getLat());
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLon());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
