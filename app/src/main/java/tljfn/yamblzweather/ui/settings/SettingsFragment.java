package tljfn.yamblzweather.ui.settings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.UIBaseData;
import tljfn.yamblzweather.ui.base.ViewModelFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class SettingsFragment extends ViewModelFragment<SettingsViewModel, UIBaseData> {

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
    public void onChanged(@Nullable UIBaseData uiBaseData) {
        // nothing so far
    }

    @Override
    protected Class<SettingsViewModel> getViewModelClass() {
        return SettingsViewModel.class;
    }
}
