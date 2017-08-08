package tljfn.yamblzweather.modules.city.favorite;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesFragment extends ViewModelFragment<FavoriteCitiesViewModel, UIFavoriteCityList> {

    @Inject
    ViewModelFactory factory;

    @Override
    public Class<FavoriteCitiesViewModel> getViewModelClass() {
        return FavoriteCitiesViewModel.class;
    }

    @Override
    protected void inject() {
        App.getComponent().inject(this);
    }

    @Override
    protected ViewModelFactory getViewModelFactory() {
        return factory;
    }

    @NonNull
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_favorite_cities;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.favorite_cities;
    }

    @Override
    public int getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(@NonNull UIFavoriteCityList data) {

    }

    @Override
    public void onError(String errorMessage) {

    }
}
