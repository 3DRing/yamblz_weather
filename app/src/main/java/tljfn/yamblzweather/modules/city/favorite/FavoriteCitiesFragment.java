package tljfn.yamblzweather.modules.city.favorite;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import tljfn.yamblzweather.App;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.fragment.ViewModelFragment;
import tljfn.yamblzweather.modules.city.CityListAdapter;
import tljfn.yamblzweather.modules.city.UICity;
import tljfn.yamblzweather.modules.city.favorite.data.UIFavoriteCityList;
import tljfn.yamblzweather.modules.weather.WeatherViewModel;
import tljfn.yamblzweather.modules.weather.data.UIWeatherData;
import tljfn.yamblzweather.utils.Utils;

/**
 * Created by ringov on 08.08.17.
 */

public class FavoriteCitiesFragment extends ViewModelFragment<FavoriteCitiesViewModel, UIFavoriteCityList> {

    public static final String TAG = FavoriteCitiesFragment.class.getName();
    @Inject
    ViewModelFactory factory;

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_city)
    TextView crtCity;
    @BindView(R.id.weather_image)
    ImageView crtWeatherImage;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_conditions)
    TextView tvConditions;

    WeatherViewModel weatherViewModel;

    @BindView(R.id.rv_favorite_cities)
    RecyclerView favoriteCities;
    CityListAdapter adapter;

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initializeRecycler();
    }

    @Override
    protected void initializeInternalViewModels() {
        super.initializeInternalViewModels();
        weatherViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(WeatherViewModel.class);
        weatherViewModel.observe(this, this::onCrtCityWeatherChanged);
    }

    private void initializeRecycler() {
        adapter = new CityListAdapter(this::onFavoriteClick, this::onCityClick);
        favoriteCities.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteCities.setAdapter(adapter);
    }

    private void onCityClick(int cityId) {
        getViewModel().onCityClicked(cityId);
    }

    private void onFavoriteClick(UICity city, boolean favorite) {
        getViewModel().onFavoriteClicked(city, favorite);
    }

    private void onCrtCityWeatherChanged(UIWeatherData uiWeatherData) {
        crtCity.setText(getString(R.string.chosen_city_text, uiWeatherData.getCity()));
        tvConditions.setText(uiWeatherData.getConditionName());
        tvTemperature.setText(getString(R.string.temperature, uiWeatherData.getTemperature()));
        crtWeatherImage.setImageResource(uiWeatherData.getConditionImage());
        tvTime.setText(Utils.getRelativeTimeInPast(getContext(), uiWeatherData.getTime()));
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
        boolean large = getResources().getBoolean(R.bool.large);
        boolean landscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        boolean landLayout = large && !landscape || !large && landscape;
        return landLayout ? R.layout.fragment_favorite_cities_land : R.layout.fragment_favorite_cities;
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
