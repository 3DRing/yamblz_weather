package tljfn.yamblzweather.model.api;

import java.util.List;

import io.reactivex.Single;
import tljfn.yamblzweather.model.api.data.city.RawCity;

/**
 * Created by ringov on 04.08.17.
 */

public interface CityApi {

    Single<List<RawCity>> getAllCities();

}
