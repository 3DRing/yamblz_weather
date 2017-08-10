package tljfn.yamblzweather.modules.city.favorite;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;
import tljfn.yamblzweather.modules.city.CitiesListAdapter;
import tljfn.yamblzweather.modules.city.favorite.data.FavoriteCity;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;
import tljfn.yamblzweather.modules.weather.WeatherViewModel;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesFragment extends ViewModelFragment<FavoriteCitiesViewModel, UIFavoriteCityList> {

    public static final String TAG = FavoriteCitiesFragment.class.getName();
    @Inject
    ViewModelFactory factory;

    @BindView(R.id.tv_crt_city)
    TextView crtCity;

    //WeatherViewModel weatherViewModel;

    @BindView(R.id.rv_favorite_cities)
    RecyclerView favoriteCities;
    CitiesListAdapter<FavoriteCity, Boolean> adapter;

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initializeRecycler();
    }

    @Override
    protected void initializeInternalViewModels() {
        super.initializeInternalViewModels();
        //weatherViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(WeatherViewModel.class);
        //weatherViewModel.observe(this, this::onCrtCityWeatherChanged);
    }

    private void initializeRecycler() {
        adapter = new FavoriteCitiesListAdapter(null);
        favoriteCities.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteCities.setAdapter(adapter);
    }

    private void onCrtCityWeatherChanged(UIWeatherData uiWeatherData) {
        crtCity.setText(uiWeatherData.getCity());
    }

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
        adapter.setItems(data.getFavoriteCities());
    }

    @Override
    public void onError(String errorMessage) {

    }
}
