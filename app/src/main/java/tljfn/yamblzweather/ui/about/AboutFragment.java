package tljfn.yamblzweather.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tljfn.yamblzweather.R;
import utils.BaseFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class AboutFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public String getFragmentTag() {
        return AboutFragment.class.getCanonicalName();
    }

    @Override
    public String getToolbarTitle() {
        return "О приложении";
    }
}
