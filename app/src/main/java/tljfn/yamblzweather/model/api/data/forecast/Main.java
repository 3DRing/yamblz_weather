
package tljfn.yamblzweather.model.api.data.forecast;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    double temp;
    @SerializedName("temp_min")
    double tempMin;
    @SerializedName("temp_max")
    double tempMax;
    @SerializedName("pressure")
    double pressure;
    @SerializedName("sea_level")
    double seaLevel;
    @SerializedName("grnd_level")
    double grndLevel;
    @SerializedName("humidity")
    double humidity;
    @SerializedName("temp_kf")
    double tempKf;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(double grndLevel) {
        this.grndLevel = grndLevel;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempKf() {
        return tempKf;
    }

    public void setTempKf(int tempKf) {
        this.tempKf = tempKf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        if (Double.compare(main.getTemp(), getTemp()) != 0) return false;
        if (Double.compare(main.getTempMin(), getTempMin()) != 0) return false;
        if (Double.compare(main.getTempMax(), getTempMax()) != 0) return false;
        if (Double.compare(main.getPressure(), getPressure()) != 0) return false;
        if (Double.compare(main.getSeaLevel(), getSeaLevel()) != 0) return false;
        if (Double.compare(main.getGrndLevel(), getGrndLevel()) != 0) return false;
        if (Double.compare(main.getHumidity(), getHumidity()) != 0) return false;
        return Double.compare(main.getTempKf(), getTempKf()) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        temp1 = Double.doubleToLongBits(getTemp());
        result = (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getTempMin());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getTempMax());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getPressure());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getSeaLevel());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getGrndLevel());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getHumidity());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        temp1 = Double.doubleToLongBits(getTempKf());
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        return result;
    }
}
