package tljfn.yamblzweather.ui.settings;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import tljfn.yamblzweather.R;
import utils.BaseFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class SettingsFragment extends BaseFragment {

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    public String getToolbarTitle() {
        return "Настройки";
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }
}
