package tljfn.yamblzweather.modules.forecast;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;

/**
 * Created by ringov on 09.08.17.
 */

public class ForecastFragment extends ViewModelFragment<ForecastViewModel, UIForecast> {

    public static final String TAG = ForecastFragment.class.getName();
    @Inject
    ViewModelFactory factory;

    @Override
    public Class<ForecastViewModel> getViewModelClass() {
        return ForecastViewModel.class;
    }

    @NonNull
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_forecast;
    }

    @Override
    public int getToolbarTitle() {
        return 0;
    }

    @Override
    public int getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    protected void inject() {
        App.getComponent().inject(this);
    }

    @Override
    protected ViewModelFactory getViewModelFactory() {
        return factory;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess(@NonNull UIForecast data) {

    }

    @Override
    public void onError(String errorMessage) {

    }
}
