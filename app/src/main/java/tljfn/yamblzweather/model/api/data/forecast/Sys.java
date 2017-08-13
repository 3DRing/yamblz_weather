
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("pod")
    String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        return getPod() != null ? getPod().equals(sys.getPod()) : sys.getPod() == null;

    }

    @Override
    public int hashCode() {
        return getPod() != null ? getPod().hashCode() : 0;
    }
}
