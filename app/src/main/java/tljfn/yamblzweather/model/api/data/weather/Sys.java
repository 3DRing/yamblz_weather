
package tljfn.yamblzweather.model.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("type")
    int type;
    @SerializedName("id")
    int id;
    @SerializedName("message")
    double message;
    @SerializedName("country")
    String country;
    @SerializedName("sunrise")
    int sunrise;
    @SerializedName("sunset")
    int sunset;

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public double getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

}
