package tljfn.yamblzweather.model.api.data.city;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawCity rawCity = (RawCity) o;

        if (yaId != rawCity.yaId) return false;
        if (openWeatherId != rawCity.openWeatherId) return false;
        if (!enName.equals(rawCity.enName)) return false;
        if (!ruName.equals(rawCity.ruName)) return false;
        return country.equals(rawCity.country);

    }

    @Override
    public int hashCode() {
        int result = yaId;
        result = 31 * result + openWeatherId;
        result = 31 * result + enName.hashCode();
        result = 31 * result + ruName.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }
}
