package tljfn.yamblzweather.model.db.cities;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Created by ringov on 04.08.17.
 */
@Dao
public interface CityDao {

    @Query("SELECT * FROM city WHERE ru_name LIKE (:requestString) OR en_name LIKE (:requestString)")
    Flowable<List<DBCity>> loadCitiesSuggestion(String requestString);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] addCities(DBCity[] cities);

    @Query("SELECT * FROM city WHERE favorite = (:favorite)")
    Flowable<List<DBCity>> loadFavoriteCities(boolean favorite);

    @Update
    int setFavorite(DBCity city);
}
