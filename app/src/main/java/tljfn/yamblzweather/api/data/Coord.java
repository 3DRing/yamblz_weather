
package tljfn.yamblzweather.api.data;

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

}
