package tljfn.yamblzweather.ui.settings;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.util.AutoClearedValue;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import tljfn.yamblzweather.R;
import tljfn.yamblzweather.databinding.FragmentSettingsBinding;

/**
 * Created by Maksim Sukhotski on 7/9/2017.
 */

public class SettingsFragment extends BaseFragment {

    private final CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private SettingsViewModel settingsViewModel;

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

    @Override
    public void onViewModelAttach() {
        settingsViewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel.class);
    }

    @Override
    public void onBindingBound(AutoClearedValue binding) {
        FragmentSettingsBinding settingsBinding = (FragmentSettingsBinding) binding.get();

        settingsViewModel.interval.observe(this, interval -> {
            int[] ints = getResources().getIntArray(R.array.intervals_seconds);
            String[] strings = getResources().getStringArray(R.array.intervals);
            for (int i = 0; i < ints.length; i++) {
                //noinspection ConstantConditions
                if (ints[i] == interval) settingsBinding.setInterval(strings[i]);
            }
            settingsBinding.executePendingBindings();
        });

        settingsBinding.setOnIntervalChangedListener((seconds) ->
                settingsViewModel.saveInterval(seconds)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }
}
