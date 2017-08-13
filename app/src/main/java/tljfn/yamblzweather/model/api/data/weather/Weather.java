
package tljfn.yamblzweather.model.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    int id;
    @SerializedName("main")
    String main;
    @SerializedName("description")
    String description;
    @SerializedName("icon")
    String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (getId() != weather.getId()) return false;
        if (!getMain().equals(weather.getMain())) return false;
        if (!getDescription().equals(weather.getDescription())) return false;
        return getIcon().equals(weather.getIcon());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getMain().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getIcon().hashCode();
        return result;
    }
}
