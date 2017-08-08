
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (getType() != sys.getType()) return false;
        if (getId() != sys.getId()) return false;
        if (Double.compare(sys.getMessage(), getMessage()) != 0) return false;
        if (getSunrise() != sys.getSunrise()) return false;
        if (getSunset() != sys.getSunset()) return false;
        return getCountry().equals(sys.getCountry());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getType();
        result = 31 * result + getId();
        temp = Double.doubleToLongBits(getMessage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getSunrise();
        result = 31 * result + getSunset();
        return result;
    }
}
