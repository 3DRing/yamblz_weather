package tljfn.yamblzweather.modules.city.choose_city;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.city.choose_city.data.UICitySuggestions;

/**
 * Created by ringov on 05.08.17.
 */

public class ChooseCityViewModel extends BaseViewModel<UICitySuggestions> {

    private Disposable suggestions;

    private ChooseCityInteractor interactor;

    @Inject
    ChooseCityViewModel(ChooseCityInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected UICitySuggestions buildUIError(String messageError) {
        return new UICitySuggestions.Builder().error(messageError).build();
    }

    public void searchCity(String requestedString) {
        disposeSuggestions();
        suggestions = interactor.getSuggestions(requestedString)
                //.debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChange, this::onError);
    }

    private void disposeSuggestions() {
        if (suggestions != null && !suggestions.isDisposed()) {
            suggestions.dispose();
        }
    }

    public void hideSearching() {
        disposeSuggestions();
        this.onChange(new UICitySuggestions.Builder().build());
    }

    public void onFavoriteClicked(int id, boolean favorite) {
        interactor.addFavorite(id, favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // todo handle result
                .subscribe();
    }
}
