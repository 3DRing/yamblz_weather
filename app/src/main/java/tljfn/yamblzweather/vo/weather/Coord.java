package tljfn.yamblzweather.vo.weather;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Coord {
    public final float lon;
    public final float lat;

    public Coord(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        if (Float.compare(coord.lon, lon) != 0) return false;
        return Float.compare(coord.lat, lat) == 0;

    }

    @Override
    public int hashCode() {
        int result = (lon != +0.0f ? Float.floatToIntBits(lon) : 0);
        result = 31 * result + (lat != +0.0f ? Float.floatToIntBits(lat) : 0);
        return result;
    }
}
