package tljfn.yamblzweather.ui.start;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.ui.NavigationController;
import butterknife.BindView;
import tljfn.yamblzweather.R;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class StartFragment extends BaseFragment {
    public static final String TAG = StartFragment.class.getName();


    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private StartViewModel startViewModel;

    @Inject
    NavigationController navigationController;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_city)
    TextView tvCity;

    @Override
    protected void initializeViews() {
        super.initializeViews();
        swipeLayout.setOnRefreshListener(() -> {
            startViewModel.updateWeather();
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
    public void onViewModelAttach() {
        startViewModel = ViewModelProviders.of(this, viewModelFactory).get(StartViewModel.class);
        startViewModel.observe(this, weather -> {
            if (weather != null) {
                tvTemperature.setText(getString(R.string.temperature, weather.getTemperature()));
                tvCity.setText(weather.getCity());
            }
            swipeLayout.setRefreshing(false);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == NavigationController.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                LatLng latLon = place.getLatLng();
                startViewModel.changeCity(latLon.latitude, latLon.longitude);
                //startViewModel.changeCity(place.getId());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getContext(), data);

                // todo handle error
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
