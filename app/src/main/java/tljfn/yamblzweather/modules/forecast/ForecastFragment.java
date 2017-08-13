package tljfn.yamblzweather.modules.forecast;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
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

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.rv_forecast)
    RecyclerView rvForecast;
    ForecastListAdapter adapter;

    boolean isLarge;

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initializeRecycler();
        swipeLayout.setOnRefreshListener(() -> getViewModel().updateForecast());
    }

    private void initializeRecycler() {
        isLarge = getResources().getBoolean(R.bool.large);
        boolean horizontalOrientationForRecycler = (getViewModel().isOrientationLandscape() && !isLarge) ||
                (!getViewModel().isOrientationLandscape() && isLarge);

        rvForecast.setLayoutManager(new LinearLayoutManager(getContext(), horizontalOrientationForRecycler ? LinearLayoutManager.HORIZONTAL :
                LinearLayoutManager.VERTICAL, false));
        adapter = new ForecastListAdapter(() ->
                (getViewModel().isOrientationLandscape() && !isLarge) ||
                        (!getViewModel().isOrientationLandscape() && isLarge), null);
        rvForecast.setAdapter(adapter);
    }

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
        return R.string.forecast;
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
        swipeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(@NonNull UIForecast data) {
        adapter.setDayForecasts(data.getDaysForecast());
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
