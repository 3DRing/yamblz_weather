package utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getToolbarTitle());
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract String getToolbarTitle();

    static <T extends BaseFragment> String doIt(T object) {
        // shake that booty
        return "";
    }
    static <T> String doIt() {
        return "";
    }
}