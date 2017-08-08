package tljfn.yamblzweather.modules.city.choose_city;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.model.repo.DatabaseRepo;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestions;
import tljfn.yamblzweather.modules.city.choose_city.data.CitySuggestion;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityViewModel extends BaseViewModel<UICitySuggestions> {

    DatabaseRepo dbRepo;

    private Disposable suggestions;

    @Inject
    public ChooseCityViewModel(DatabaseRepo dbRepo) {
        this.dbRepo = dbRepo;
    }

    @Override
    protected UICitySuggestions buildUIError(String messageError) {
        return new UICitySuggestions.Builder().error(messageError).build();
    }

    public void searchCity(String requestedString) {
        suggestions = dbRepo.getSuggestions(requestedString.toLowerCase())
                .map(list -> {
                    UICitySuggestions.Builder builder = new UICitySuggestions.Builder();
                    for (CitySuggestion s :
                            list) {
                        builder.addCity(s);
                    }
                    return builder.build();
                })
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChange, this::onError);
    }

    public void hideSearching() {
        if (suggestions != null && !suggestions.isDisposed()) {
            suggestions.dispose();
        }
        this.onChange(new UICitySuggestions.Builder().build());
    }
}
