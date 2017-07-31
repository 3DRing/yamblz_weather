package tljfn.yamblzweather.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import tljfn.yamblzweather.ui.start.UIWeatherData;
import tljfn.yamblzweather.ui.start.WeatherViewModel;

/**
 * Created by Maksim Sukhotski on 7/15/2017.
 */

public abstract class ViewModelFragment<VM extends BaseViewModel<D>, D extends UIBaseData> extends BaseFragment
        implements LifecycleRegistryOwner, Observer<D> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected VM viewModel;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    protected abstract Class<VM> getViewModelClass();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
        viewModel.observe(this, this);
    }
}