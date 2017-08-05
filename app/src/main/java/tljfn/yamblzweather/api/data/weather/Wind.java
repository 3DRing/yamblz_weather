
package tljfn.yamblzweather.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    double speed;
    @SerializedName("deg")
    double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

}
