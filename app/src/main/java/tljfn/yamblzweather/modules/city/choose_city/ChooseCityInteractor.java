package tljfn.yamblzweather.modules.city.choose_city;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import tljfn.yamblzweather.model.db.cities.DBCity;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.model.repo.PreferencesRepo;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.city.UICity;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestions;

/**
 * Created by ringov on 09.08.17.
 */

public class ChooseCityInteractor extends BaseInteractor {

    PreferencesRepo preferencesRepo;
    DatabaseRepo dbRepo;

    SparseArray<DBCity> suggestedCities;

    @Inject
    public ChooseCityInteractor(PreferencesRepo preferencesRepo, DatabaseRepo dbRepo) {
        this.preferencesRepo = preferencesRepo;
        this.dbRepo = dbRepo;
        suggestedCities = new SparseArray<>();
    }

    public Flowable<UICitySuggestions> getSuggestions(String requestedString) {
        return dbRepo.getSuggestions(requestedString.toLowerCase())
                .doOnNext(list -> {
                    suggestedCities.clear();
                })
                .flatMap(list -> Flowable.fromIterable(list)
                        .doOnNext(city -> suggestedCities.put(city.getOpenWeatherId(), city))
                        .map(UIConverter::toUISuggestions)
                        .toSortedList((city1, city2) -> city1.getName().compareTo(city2.getName()))
                        .toFlowable())
                .flatMapSingle(Single::just)
                .map(list -> {
                    UICitySuggestions.Builder builder = new UICitySuggestions.Builder();
                    for (UICity s :
                            list) {
                        builder.addCity(s);
                    }
                    return builder.build();
                });
    }

    public Single<Boolean> setFavorite(int id, boolean favorite) {
        DBCity favoriteCity = suggestedCities.get(id);
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
