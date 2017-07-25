package tljfn.yamblzweather.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tljfn.yamblzweather.vo.detailed_place_info.PlaceDetailedInfo;

/**
 * Created by ringov on 25.07.17.
 */

public interface PlacesApi {
    @GET("json")
    Single<PlaceDetailedInfo> getPlaceInfo(@Query("placeid") String placeId);
}
