package tljfn.yamblzweather.api.data;

/**
 * Created by Maksim Sukhotski on 7/17/2017.
 */

public class Clouds {
    public final String all;

    public Clouds(String all) {
        this.all = all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clouds clouds = (Clouds) o;

        return all.equals(clouds.all);

    }

    @Override
    public int hashCode() {
        return all.hashCode();
    }
}
