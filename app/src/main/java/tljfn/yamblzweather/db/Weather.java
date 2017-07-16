package tljfn.yamblzweather.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Immutable model class for a Weather
 */
@Entity(tableName = "weather")
public class Weather {

    @PrimaryKey
    @ColumnInfo(name = "weatherId")
    private Long id;

    @ColumnInfo(name = "value")
    private Integer value;

    public Weather(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }
}
