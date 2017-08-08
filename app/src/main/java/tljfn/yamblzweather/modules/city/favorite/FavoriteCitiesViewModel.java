package tljfn.yamblzweather.modules.city.favorite;

import javax.inject.Inject;

import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesViewModel extends BaseViewModel<UIFavoriteCityList> {

    FavoriteCitiesInteractor interactor;

    @Inject
    public FavoriteCitiesViewModel(FavoriteCitiesInteractor interactor) {
        this.interactor = interactor;
        sub(interactor.loadFavoriteCities()
                .subscribe(this::test, this::onError));
    }

    private void test(UIFavoriteCityList list){
        this.onChange(list);
    }

    @Override
    protected UIFavoriteCityList buildUIError(String messageError) {
        return new UIFavoriteCityList.Builder().error(messageError).build();
    }
}
