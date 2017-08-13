
package tljfn.yamblzweather.model.api.data.weather;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    double temp;
    @SerializedName("pressure")
    double pressure;
    @SerializedName("humidity")
    double humidity;
    @SerializedName("temp_min")
    double tempMin;
    @SerializedName("temp_max")
    double tempMax;

    public double getTemp() {
        return temp;
    }


    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (Double.compare(main.getTemp(), getTemp()) != 0) return false;
        if (Double.compare(main.getPressure(), getPressure()) != 0) return false;
        if (Double.compare(main.getHumidity(), getHumidity()) != 0) return false;
        if (Double.compare(main.getTempMin(), getTempMin()) != 0) return false;
        return Double.compare(main.getTempMax(), getTempMax()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        temp1 = Double.doubleToLongBits(getTemp());
        result = (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getPressure());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getHumidity());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getTempMin());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getTempMax());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        return result;
    }
}
