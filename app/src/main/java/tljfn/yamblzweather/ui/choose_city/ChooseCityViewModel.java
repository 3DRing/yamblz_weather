package tljfn.yamblzweather.ui.choose_city;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import tljfn.yamblzweather.App;
import tljfn.yamblzweather.repo.DatabaseRepo;
import tljfn.yamblzweather.ui.base.BaseViewModel;
import tljfn.yamblzweather.ui.choose_city.data.CitySuggestions;
import tljfn.yamblzweather.ui.choose_city.data.UICitySuggestion;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityViewModel extends BaseViewModel<CitySuggestions> {

    @Inject
    DatabaseRepo dbRepo;

    ChooseCityViewModel() {
        App.getComponent().inject(this);
    }

    @Override
    protected CitySuggestions buildUIError(String messageError) {
        return new CitySuggestions.Builder().error(messageError).build();
    }

    public void searchCity(String requestedString) {
        dbRepo.getSuggestions(requestedString.toLowerCase())
                .debounce(1, TimeUnit.SECONDS)
                .map(list -> {
                    CitySuggestions.Builder builder = new CitySuggestions.Builder();
                    for (UICitySuggestion s :
                            list) {
                        builder.addCity(s);
                    }
                    return builder.build();
                })
                .subscribe(this::onChange, this::onError);
    }
}
