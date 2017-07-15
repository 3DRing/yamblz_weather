package tljfn.yamblzweather.ui.start;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import tljfn.yamblzweather.R;
import utils.BaseFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class StartFragment extends BaseFragment {

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
}
