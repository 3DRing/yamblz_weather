
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

}
