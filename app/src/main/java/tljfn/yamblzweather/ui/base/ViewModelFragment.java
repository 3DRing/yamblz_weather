package tljfn.yamblzweather.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.data.UIBaseData;

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

    @Override
    public void onChanged(@Nullable D data) {
        if (data != null) {
            if (data.hasError()) {
                onError(data.getErrorMessage());
            } else {
                onSuccess(data);
            }
        } else {
            onError(getString(R.string.no_data_loaded_error));
        }
        hideLoading();
    }

    protected abstract void showLoading();

    protected abstract void hideLoading();

    protected abstract void onSuccess(@NonNull D data);

    protected abstract void onError(String errorMessage);
}