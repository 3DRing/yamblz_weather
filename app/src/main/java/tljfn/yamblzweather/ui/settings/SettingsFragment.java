package tljfn.yamblzweather.ui.settings;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import tljfn.yamblzweather.ui.base.BaseFragment;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.UIBaseData;
import tljfn.yamblzweather.ui.base.ViewModelFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class SettingsFragment extends ViewModelFragment<UIBaseData> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private SettingsViewModel settingsViewModel;

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.settings_screen_title;
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onViewModelAttach() {
        settingsViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel.class);
    }
}
