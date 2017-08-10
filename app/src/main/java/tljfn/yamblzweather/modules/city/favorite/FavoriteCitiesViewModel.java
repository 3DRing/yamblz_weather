package tljfn.yamblzweather.modules.city.favorite;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.city.UICity;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesViewModel extends BaseViewModel<UIFavoriteCityList> {

    FavoriteCitiesInteractor interactor;

    @Inject
    public FavoriteCitiesViewModel(FavoriteCitiesInteractor interactor) {
        this.interactor = interactor;
        loadFavoriteCities();
    }

    @Override
    protected UIFavoriteCityList buildUIError(String messageError) {
        return new UIFavoriteCityList.Builder().error(messageError).build();
    }

    protected void loadFavoriteCities() {
        interactor.loadFavoriteCities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChange, this::onError);
    }

    public void onCityClicked(int cityId) {
        interactor.chooseCity(cityId)
                .subscribe();
    }

    public void onFavoriteClicked(UICity city, boolean favorite) {
        interactor.setFavorite(city.getId(), favorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // todo handle result
                .subscribe();
    }
}
