
package tljfn.yamblzweather.api.data.weather;

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

}
