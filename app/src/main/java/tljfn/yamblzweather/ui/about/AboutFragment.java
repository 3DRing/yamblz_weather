package tljfn.yamblzweather.ui.about;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.data.UIBaseData;
import tljfn.yamblzweather.ui.base.ViewModelFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class AboutFragment extends ViewModelFragment<AboutViewModel, UIBaseData> {

    public static final String TAG = "about";

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
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    protected Class<AboutViewModel> getViewModelClass() {
        return AboutViewModel.class;
    }

    @Override
    protected void showLoading() {

    }

    @Override
    protected void hideLoading() {

    }

    @Override
    protected void onSuccess(UIBaseData data) {

    }

    @Override
    protected void onError(String errorMessage) {

    }
}
