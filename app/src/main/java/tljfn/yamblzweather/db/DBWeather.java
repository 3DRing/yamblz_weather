package tljfn.yamblzweather.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ringov on 01.08.17.
 */

@Entity(tableName = "weather")
public class DBWeather {

    @PrimaryKey
    int id;
}
