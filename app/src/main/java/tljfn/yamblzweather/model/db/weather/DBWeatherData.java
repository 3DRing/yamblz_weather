package tljfn.yamblzweather.model.db.weather;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import tljfn.yamblzweather.model.api.data.weather.RawWeather;
import tljfn.yamblzweather.model.api.data.weather.Weather;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.modules.weather.data.WeatherCondition;

/**
 * Created by ringov on 01.08.17.
 */

@Entity(tableName = "weather")
public class DBWeatherData {

    @PrimaryKey
    private int id;
    private String city;
    private double temperature;
    private long time;
    private int condition;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDegree;

    public DBWeatherData() {
        id = -1;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(double windDegree) {
        this.windDegree = windDegree;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBWeatherData that = (DBWeatherData) o;

        if (getId() != that.getId()) return false;
        if (Double.compare(that.getTemperature(), getTemperature()) != 0) return false;
        if (getCondition() != that.getCondition()) return false;
        if (Double.compare(that.getPressure(), getPressure()) != 0) return false;
        if (Double.compare(that.getHumidity(), getHumidity()) != 0) return false;
        if (Double.compare(that.getWindSpeed(), getWindSpeed()) != 0) return false;
        if (Double.compare(that.getWindDegree(), getWindDegree()) != 0) return false;
        return getCity() != null ? getCity().equals(that.getCity()) : that.getCity() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId();
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        temp = Double.doubleToLongBits(getTemperature());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (getTime() ^ (getTime() >>> 32));
        result = 31 * result + getCondition();
        temp = Double.doubleToLongBits(getPressure());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getHumidity());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getWindSpeed());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getWindDegree());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class Builder {

        DBWeatherData data;

        public Builder() {
            data = new DBWeatherData();
        }

        public Builder city(String city) {
            data.city = city;
            return this;
        }

        public Builder temperature(double temperature) {
            data.temperature = temperature;
            return this;
        }

        public Builder time(long time) {
            data.time = time;
            return this;
        }

        public Builder condition(int condition) {
            data.condition = condition;
            return this;
        }

        public Builder pressure(double pressure) {
            data.pressure = pressure;
            return this;
        }

        public Builder humidity(double humidity) {
            data.humidity = humidity;
            return this;
        }

        public Builder windDegree(double degree) {
            data.windDegree = degree;
            return this;
        }

        public Builder windSpeed(double windSpeed) {
            data.windSpeed = windSpeed;
            return this;
        }

        public DBWeatherData build() {
            return data;
        }

        public Builder id(int openWeatherId) {
            data.id = openWeatherId;
            return this;
        }
    }
}
