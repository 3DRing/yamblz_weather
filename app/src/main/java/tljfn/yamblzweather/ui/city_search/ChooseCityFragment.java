package tljfn.yamblzweather.ui.city_search;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.util.AutoClearedValue;
import tljfn.yamblzweather.R;

/**
 * Created by ringov on 24.07.17.
 */

public class ChooseCityFragment extends BaseFragment implements GoogleApiClient.OnConnectionFailedListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ChooseCityViewModel viewModel;

    GoogleApiClient apiClient;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(getActivity(), this)
                .build();
    }

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_choose_city;
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.choose_city);
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onViewModelAttach() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChooseCityViewModel.class);
    }

    @Override
    public void onBindingCreated(AutoClearedValue binding) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
