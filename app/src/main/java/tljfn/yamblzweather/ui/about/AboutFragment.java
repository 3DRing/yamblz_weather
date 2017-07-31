package tljfn.yamblzweather.ui.about;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import arch.ui.BaseFragment;
import tljfn.yamblzweather.R;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class AboutFragment extends BaseFragment {

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_about;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.about_screen_title;
    }

    @Override
    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
    }

    @Override
    public void onViewModelAttach() {

    }
}
