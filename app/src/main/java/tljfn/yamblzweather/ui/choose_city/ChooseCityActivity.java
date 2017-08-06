package tljfn.yamblzweather.ui.choose_city;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.BaseFragment;

/**
 * Created by ringov on 04.08.17.
 */

public class ChooseCityActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        ButterKnife.bind(this);
        startFragment();
    }

    private void startFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(ChooseCityFragment.TAG) == null) {
            fm.beginTransaction().replace(R.id.choose_city_container, new ChooseCityFragment()).commit();
        }
    }

    @Override
    public void onFragmentInteraction(@StringRes int titleRes, Integer drawerMode) {

    }
}
