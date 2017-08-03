package tljfn.yamblzweather.ui.brand_new_settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.di.Injectable;
import tljfn.yamblzweather.repo.PreferencesRepo;
import tljfn.yamblzweather.ui.base.BaseFragment;

/**
 * Created by ringov on 07.07.17.
 */

public class BrandNewSettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener, Injectable {

    public static final String TAG = "settings";
    @Inject
    PreferencesRepo settings;
    SharedPreferences prefs;

    protected BaseFragment.OnFragmentInteractionListener onFragmentInteractionListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentInteractionListener.onFragmentInteraction(getToolbarTitle(), getDrawerMode());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    public int getToolbarTitle() {
        return R.string.settings_screen_title;
    }

    public Integer getDrawerMode() {
        return DrawerLayout.LOCK_MODE_UNLOCKED;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onFragmentInteractionListener = (BaseFragment.OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        settings.onPreferencesChanged(sharedPreferences, s);
    }
}
