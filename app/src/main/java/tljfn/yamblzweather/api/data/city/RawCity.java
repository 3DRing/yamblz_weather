package tljfn.yamblzweather.api.data.city;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ringov on 04.08.17.
 */

public class RawCity {
    @SerializedName("yaId")
    public int yaId;
    @SerializedName("openWeatherId")
    public int openWeatherId;
    @SerializedName("enName")
    public String enName;
    @SerializedName("ruName")
    public String ruName;
    @SerializedName("country")
    public String country;
}
