
package tljfn.yamblzweather.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    int all;

    public int getAll() {
        return all;
    }
}
