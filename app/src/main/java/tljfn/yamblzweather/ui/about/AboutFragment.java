package tljfn.yamblzweather.ui.about;

import android.support.annotation.NonNull;

import tljfn.yamblzweather.R;
import utils.BaseFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class AboutFragment extends BaseFragment {

    public static String tag = AboutFragment.class.toString();

    @NonNull
    @Override
    public Integer getLayoutRes() {
        return R.layout.fragment_about;
    }

    @Override
    public String getToolbarTitle() {
        return "О приложении";
    }
}
