package tljfn.yamblzweather.modules.forecast;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initializeRecycler();
        swipeLayout.setOnRefreshListener(() -> getViewModel().updateForecast());
    }

    private void initializeRecycler() {
        rvForecast.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ForecastListAdapter(null);
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
