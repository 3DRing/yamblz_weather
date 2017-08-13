package tljfn.yamblzweather.modules.city.favorite;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesInteractor extends BaseInteractor {

    PreferencesRepo preferencesRepo;
    DatabaseRepo dbRepo;

    SparseArray<DBCity> favoriteCities;

    @Inject
    public FavoriteCitiesInteractor(PreferencesRepo preferencesRepo, DatabaseRepo dbRepo) {
        this.preferencesRepo = preferencesRepo;
        this.dbRepo = dbRepo;
        favoriteCities = new SparseArray<>();
    }

    public Flowable<UIFavoriteCityList> loadFavoriteCities() {
        return dbRepo.loadFavoriteCities()
                .flatMap(list ->
                        Flowable.fromIterable(list)
                                .doOnNext(next -> favoriteCities.put(next.getOpenWeatherId(), next))
                                .map(UIConverter::toFavoriteCity)
                                .toSortedList((city1, city2) -> city1.getName().compareTo(city2.getName()))
                                .toFlowable())
                .map(list -> new UIFavoriteCityList.Builder()
                        .addAllCities(list)
                        .build());
    }

    public Single<Boolean> setFavorite(int id, boolean favorite) {
        DBCity favoriteCity = favoriteCities.get(id);
        if (favoriteCity != null) {
            favoriteCity.setFavorite(favorite);
            return dbRepo.setFavorite(favoriteCity);
        } else {
            return Single.just(false);
        }
    }

    public Completable chooseCity(int id) {
        preferencesRepo.updateCurrentCity(id);
        return Completable.complete();
    }
}
