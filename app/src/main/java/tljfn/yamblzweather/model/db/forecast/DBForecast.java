package tljfn.yamblzweather.model.db.forecast;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ringov on 10.08.17.
 */

@Entity(tableName = "forecast")
public class DBForecast {

    public static long generateId(int cityId, int count) {
        return cityId * 1000 + count; // unique id dependant on the city id and with unique part at 3 lower registers
    }

    @PrimaryKey
    @ColumnInfo(name = "id")
    long id;
    @ColumnInfo(name = "city_id")
    int cityId;
    @ColumnInfo(name = "city_name")
    String cityName;
    @ColumnInfo(name = "update_time")
    long updateTime;
    @ColumnInfo(name = "forecast_time")
    long forecastTime;
    @ColumnInfo(name = "temperature")
    double temperature;
    @ColumnInfo(name = "condition_id")
    int conditionId;

    public DBForecast() {

    }

    private DBForecast(DBForecast otherForecast) {
        this.id = otherForecast.getId();
        this.cityId = otherForecast.getCityId();
        this.cityName = otherForecast.getCityName();
        this.updateTime = otherForecast.getUpdateTime();
        this.forecastTime = otherForecast.getForecastTime();
        this.temperature = otherForecast.getTemperature();
        this.conditionId = otherForecast.getConditionId();
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(long forecastTime) {
        this.forecastTime = forecastTime;
    }

    public int getConditionId() {
        return conditionId;
    }

    public void setConditionId(int conditionId) {
        this.conditionId = conditionId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBForecast that = (DBForecast) o;

        if (getId() != that.getId()) return false;
        if (getCityId() != that.getCityId()) return false;
        if (Double.compare(that.getTemperature(), getTemperature()) != 0) return false;
        if (getConditionId() != that.getConditionId()) return false;
        return getCityName() != null ? getCityName().equals(that.getCityName()) : that.getCityName() == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getCityId();
        result = 31 * result + (getCityName() != null ? getCityName().hashCode() : 0);
        temp = Double.doubleToLongBits(getTemperature());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getConditionId();
        return result;
    }

    public static class Builder {

        DBForecast forecast;

        public Builder() {
            forecast = new DBForecast();
        }

        public Builder id(long id) {
            this.forecast.id = id;
            return this;
        }

        public Builder cityId(int cityId) {
            this.forecast.cityId = cityId;
            return this;
        }

        public Builder city(String name) {
            this.forecast.cityName = name;
            return this;
        }

        public Builder updateTime(long time) {
            this.forecast.updateTime = time;
            return this;
        }

        public Builder forecastTime(long time) {
            this.forecast.forecastTime = time;
            return this;
        }

        public Builder temperature(double temperature) {
            this.forecast.temperature = temperature;
            return this;
        }

        public Builder condition(int conditionId) {
            this.forecast.conditionId = conditionId;
            return this;
        }

        public DBForecast build() {
            return forecast;
        }

        public DBForecast buildClone() {
            return new DBForecast(forecast);
        }
    }
}
