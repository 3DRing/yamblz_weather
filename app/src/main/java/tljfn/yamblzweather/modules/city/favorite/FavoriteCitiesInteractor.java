package tljfn.yamblzweather.modules.city.favorite;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesInteractor extends BaseInteractor {

    DatabaseRepo dbRepo;

    @Inject
    public FavoriteCitiesInteractor(DatabaseRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    public Flowable<UIFavoriteCityList> loadFavoriteCities() {
        return dbRepo.loadFavoriteCities()
                .flatMap(list ->
                        Flowable.fromIterable(list)
                        .map(UIConverter::toFavoriteCity)
                        .toSortedList((city1, city2) -> city1.getName().compareTo(city2.getName()))
                        .toFlowable())
                .map(list -> new UIFavoriteCityList.Builder()
                        .addAllCities(list)
                        .build());
    }
}
