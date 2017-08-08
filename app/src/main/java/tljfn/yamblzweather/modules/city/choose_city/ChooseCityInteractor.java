package tljfn.yamblzweather.modules.city.choose_city;

import javax.inject.Inject;

import io.reactivex.Flowable;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.modules.base.BaseInteractor;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestions;

/**
 * Created by ringov on 09.08.17.
 */

public class ChooseCityInteractor extends BaseInteractor {

    DatabaseRepo dbRepo;

    @Inject
    public ChooseCityInteractor(DatabaseRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    public Flowable<UICitySuggestions> getSuggestions(String requestedString) {
        return dbRepo.getSuggestions(requestedString.toLowerCase())
                .map(list -> {
                    UICitySuggestions.Builder builder = new UICitySuggestions.Builder();
                    for (CitySuggestion s :
                            list) {
                        builder.addCity(s);
                    }
                    return builder.build();
                });
    }
}
