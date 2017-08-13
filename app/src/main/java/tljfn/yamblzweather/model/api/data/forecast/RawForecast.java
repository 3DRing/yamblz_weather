
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RawForecast {

    @SerializedName("cod")
    String cod;
    @SerializedName("message")
    double message;
    @SerializedName("cnt")
    int cnt;
    @SerializedName("list")
    List<SingularForecast> list = null;
    @SerializedName("city")
    City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<SingularForecast> getList() {
        return list;
    }

    public void setList(List<SingularForecast> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawForecast that = (RawForecast) o;

        if (Double.compare(that.getMessage(), getMessage()) != 0) return false;
        if (getCnt() != that.getCnt()) return false;
        if (getCod() != null ? !getCod().equals(that.getCod()) : that.getCod() != null)
            return false;
        if (getList() != null ? !getList().equals(that.getList()) : that.getList() != null)
            return false;
        return getCity() != null ? getCity().equals(that.getCity()) : that.getCity() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getCod() != null ? getCod().hashCode() : 0;
        temp = Double.doubleToLongBits(getMessage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getCnt();
        result = 31 * result + (getList() != null ? getList().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }
}
