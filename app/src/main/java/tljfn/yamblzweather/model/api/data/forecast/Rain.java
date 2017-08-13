
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("3h")
    @Expose
    private double _3h;

    public double get3h() {
        return _3h;
    }

    public void set3h(double _3h) {
        this._3h = _3h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rain rain = (Rain) o;

        return Double.compare(rain._3h, _3h) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(_3h);
        return (int) (temp ^ (temp >>> 32));
    }
}
