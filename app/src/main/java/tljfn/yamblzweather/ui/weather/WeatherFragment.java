package tljfn.yamblzweather.ui.weather;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import tljfn.yamblzweather.ui.weather.data.UIWeatherData;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class WeatherFragment extends ViewModelFragment<WeatherViewModel, UIWeatherData> {
    public static final String TAG = WeatherFragment.class.getName();

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_city)
    TextView tvCity;

    @OnClick(R.id.tv_city)
    public void onCityClick() {
        NavigationController.navigateToChooseCity(R.id.fragment_container, getActivity().getSupportFragmentManager());
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        swipeLayout.setOnRefreshListener(() -> viewModel.updateWeather());
    }

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_weather;
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
    protected Class<WeatherViewModel> getViewModelClass() {
        return WeatherViewModel.class;
    }

    @Override
    protected void showLoading() {
        swipeLayout.setRefreshing(true);
    }

    @Override
    protected void hideLoading() {
        swipeLayout.setRefreshing(false);
    }

    @Override
    protected void onSuccess(@NonNull UIWeatherData data) {
        tvTemperature.setText(getString(R.string.temperature, data.getTemperature()));
        tvCity.setText(data.getCity());
    }

    @Override
    protected void onError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
