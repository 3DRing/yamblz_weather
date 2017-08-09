package tljfn.yamblzweather.model.db.cities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ringov on 04.08.17.
 */

@Entity(tableName = "city", indices = {@Index("ru_name"), @Index("en_name"), @Index("favorite")})
public class DBCity {

    @PrimaryKey
    @SerializedName("yaId")
    private int id;

    @ColumnInfo(name = "open_weather_id")
    @SerializedName("openWeatherId")
    int openWeatherId;

    @ColumnInfo(name = "ru_name")
    @SerializedName("ruName")
    String ruName;

    @ColumnInfo(name = "en_name")
    @SerializedName("enName")
    String enName;

    @ColumnInfo(name = "country_code")
    @SerializedName("countryCode")
    String countryCode;
    @ColumnInfo(name = "favorite")
    boolean favorite;

    public int getId() {
        return id;
    }

    public void setId(int yaId) {
        this.id = yaId;
    }

    public int getOpenWeatherId() {
        return openWeatherId;
    }

    public void setOpenWeatherId(int openWeatherId) {
        this.openWeatherId = openWeatherId;
    }

    public String getRuName() {
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBCity dbCity = (DBCity) o;

        if (getId() != dbCity.getId()) return false;
        if (getOpenWeatherId() != dbCity.getOpenWeatherId()) return false;
        if (isFavorite() != dbCity.isFavorite()) return false;
        if (getRuName() != null ? !getRuName().equals(dbCity.getRuName()) : dbCity.getRuName() != null)
            return false;
        if (getEnName() != null ? !getEnName().equals(dbCity.getEnName()) : dbCity.getEnName() != null)
            return false;
        return getCountryCode() != null ? getCountryCode().equals(dbCity.getCountryCode()) : dbCity.getCountryCode() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getOpenWeatherId();
        result = 31 * result + (getRuName() != null ? getRuName().hashCode() : 0);
        result = 31 * result + (getEnName() != null ? getEnName().hashCode() : 0);
        result = 31 * result + (getCountryCode() != null ? getCountryCode().hashCode() : 0);
        result = 31 * result + (isFavorite() ? 1 : 0);
        return result;
    }

    public static class Builder {
        private DBCity city;

        public Builder() {
            this.city = new DBCity();
        }

        public Builder id(int id) {
            city.id = id;
            return this;
        }

        public Builder openWeatherId(int id) {
            city.openWeatherId = id;
            return this;
        }

        public Builder ruName(String ruName) {
            city.ruName = ruName;
            return this;
        }

        public Builder enName(String enName) {
            city.enName = enName;
            return this;
        }

        public Builder countryCode(String code) {
            city.countryCode = code;
            return this;
        }

        public Builder favorite(boolean favorite) {
            city.favorite = favorite;
            return this;
        }

        public DBCity build() {
            return city;
        }
    }
}
