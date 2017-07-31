package tljfn.yamblzweather.ui.start;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import tljfn.yamblzweather.navigation.NavigationController;
import butterknife.BindView;
import butterknife.OnClick;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.ViewModelFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class WeatherFragment extends ViewModelFragment<WeatherViewModel, UIWeatherData> {
    public static final String TAG = WeatherFragment.class.getName();

    @Inject
    NavigationController navigationController;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_city)
    TextView tvCity;

    @OnClick(R.id.tv_city)
    public void onCityClick() {
        navigationController.navigateToChooseCity();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        swipeLayout.setOnRefreshListener(() -> {
            viewModel.updateWeather();
        });
    }

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_start;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.weather_screen_title;
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NavigationController.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                LatLng latLon = place.getLatLng();
                viewModel.changeCity(latLon.latitude, latLon.longitude);
                //weatherViewModel.changeCity(place.getId());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);

                // todo handle error
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public void onChanged(@Nullable UIWeatherData uiWeatherData) {
        if (uiWeatherData != null) {
            tvTemperature.setText(getString(R.string.temperature, uiWeatherData.getTemperature()));
            tvCity.setText(uiWeatherData.getCity());
        }
        swipeLayout.setRefreshing(false);
    }

    @Override
    protected Class<WeatherViewModel> getViewModelClass() {
        return WeatherViewModel.class;
    }
}
