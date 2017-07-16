package tljfn.yamblzweather.ui.settings;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;

import javax.inject.Inject;

import arch.ui.BaseFragment;
import arch.util.AutoClearedValue;
import io.reactivex.disposables.CompositeDisposable;
import tljfn.yamblzweather.R;

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
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_inrerval);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.intervals_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                disposable.add(settingsViewModel.updateInterval(i)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe((Consumer<String>) userName -> mUserName.setText(userName),
//                                (Consumer<Throwable>) throwable -> Log.e(TAG, "Unable to update username", throwable)));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }
}
