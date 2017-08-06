package tljfn.yamblzweather.model.db.cities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

import tljfn.yamblzweather.model.api.data.city.RawCity;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestion;

/**
 * Created by ringov on 04.08.17.
 */

@Entity(tableName = "city", indices = {@Index("ru_name"), @Index("en_name")})
public class DBCity {

    public static UICitySuggestion toUISuggestions(DBCity city) {
        String locale = Locale.getDefault().getLanguage();
        String name;
        // todo differentiate languages in more generic way
        if (locale.equals("ru")) {
            name = city.getRuName();
        } else {
            name = city.getEnName();
        }
        StringBuilder sb = new StringBuilder();
        name = sb.append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).toString();
        return new UICitySuggestion(city.getOpenWeatherId(), name);
    }

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

    public static DBCity fromRawCity(RawCity city) {
        return new DBCity.Builder()
                .id(city.yaId)
                .openWeatherId(city.openWeatherId)
                .ruName(city.ruName.toLowerCase())
                .enName(city.enName.toLowerCase())
                .countryCode(city.country.toLowerCase())
                .favorite(false)
                .build();
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

        Builder openWeatherId(int id) {
            city.openWeatherId = id;
            return this;
        }

        Builder ruName(String ruName) {
            city.ruName = ruName;
            return this;
        }

        Builder enName(String enName) {
            city.enName = enName;
            return this;
        }

        Builder countryCode(String code) {
            city.countryCode = code;
            return this;
        }

        Builder favorite(boolean favorite) {
            city.favorite = favorite;
            return this;
        }

        DBCity build() {
            return city;
        }
    }
}
