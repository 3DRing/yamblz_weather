package tljfn.yamblzweather.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tljfn.yamblzweather.R;
import utils.BaseFragment;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class SettingsFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public String getFragmentTag() {
        return SettingsFragment.class.getCanonicalName();
    }

    @Override
    public String getToolbarTitle() {
        return "Настройки";
    }
}
