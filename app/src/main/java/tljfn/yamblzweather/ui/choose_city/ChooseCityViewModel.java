package tljfn.yamblzweather.ui.choose_city;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
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

    Disposable suggestions;

    ChooseCityViewModel() {
        App.getComponent().inject(this);
    }

    @Override
    protected CitySuggestions buildUIError(String messageError) {
        return new CitySuggestions.Builder().error(messageError).build();
    }

    public void searchCity(String requestedString) {
        suggestions = dbRepo.getSuggestions(requestedString.toLowerCase())
                .map(list -> {
                    CitySuggestions.Builder builder = new CitySuggestions.Builder();
                    for (UICitySuggestion s :
                            list) {
                        builder.addCity(s);
                    }
                    return builder.build();
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(this::onChange, this::onError);
    }

    public void hideSearching() {
        if (suggestions != null && !suggestions.isDisposed()) {
            suggestions.dispose();
        }
        this.onChange(new CitySuggestions.Builder().build());
    }
}
