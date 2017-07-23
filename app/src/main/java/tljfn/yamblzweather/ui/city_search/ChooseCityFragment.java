package tljfn.yamblzweather.ui.city_search;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.util.AutoClearedValue;
import tljfn.yamblzweather.R;

/**
 * Created by ringov on 24.07.17.
 */

public class ChooseCityFragment extends BaseFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ChooseCityViewModel viewModel;

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
}
