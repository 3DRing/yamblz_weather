package tljfn.yamblzweather.ui.start;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.ui.NavigationController;
import arch.util.AutoClearedValue;
import io.reactivex.disposables.CompositeDisposable;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.databinding.FragmentStartBinding;

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

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_start;
    }

    @Override
    public String getToolbarTitle() {
        return "Стартовый экран";
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    public void onViewModelAttach() {
        startViewModel = ViewModelProviders.of(this, viewModelFactory).get(StartViewModel.class);
    }

    @Override
    public void onBindingCreated(AutoClearedValue binding) {
        FragmentStartBinding startBinding = (FragmentStartBinding) binding.get();

        startViewModel.weather.observe(this, weather -> {
            startBinding.setWeather(weather);
            startBinding.executePendingBindings();
        });

        startBinding.setOnRefreshListener(startViewModel::updateWeather);
        startBinding.setOnChooseCityCallback(() -> navigationController.navigateToChooseCity());
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
