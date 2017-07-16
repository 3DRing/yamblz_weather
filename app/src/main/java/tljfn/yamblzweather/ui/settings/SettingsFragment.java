package tljfn.yamblzweather.ui.settings;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.util.AutoClearedValue;
import io.reactivex.disposables.CompositeDisposable;
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

        settingsBinding.setOnIntervalChangedListener((v) -> Toast.makeText(getContext(), v, Toast.LENGTH_SHORT).show());
    }
}
