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
        if (enName != null ? !enName.equals(rawCity.enName) : rawCity.enName != null) return false;
        if (ruName != null ? !ruName.equals(rawCity.ruName) : rawCity.ruName != null) return false;
        return country != null ? country.equals(rawCity.country) : rawCity.country == null;

    }

    @Override
    public int hashCode() {
        int result = yaId;
        result = 31 * result + openWeatherId;
        result = 31 * result + (enName != null ? enName.hashCode() : 0);
        result = 31 * result + (ruName != null ? ruName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
