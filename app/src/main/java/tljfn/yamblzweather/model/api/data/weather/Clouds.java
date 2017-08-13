
package tljfn.yamblzweather.model.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    int all;

    public int getAll() {
        return all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return getAll() == clouds.getAll();

    }

    @Override
    public int hashCode() {
        return getAll();
    }
}
