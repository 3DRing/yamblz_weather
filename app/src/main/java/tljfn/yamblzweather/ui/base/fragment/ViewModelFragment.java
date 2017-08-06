package tljfn.yamblzweather.ui.base.fragment;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.LoadingScreen;
import tljfn.yamblzweather.ui.base.UIErrorShower;
import tljfn.yamblzweather.ui.base.data.UIBaseData;
import tljfn.yamblzweather.ui.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.ui.base.viewmodel.ViewModelScreen;

public abstract class ViewModelFragment<VM extends BaseViewModel<D>, D extends UIBaseData> extends BaseFragment
        implements ViewModelScreen<VM, D>, LoadingScreen<D>, LifecycleRegistryOwner {

    private UIErrorShower<D> errorShower;
    private VM viewModel;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(getViewModelClass());
        viewModel.observe(this, this::onChanged);
        errorShower = new UIErrorShower<>();
    }

    protected void onChanged(@Nullable D data) {
        errorShower.showError(getContext(), this, data);
    }

    @Override
    public VM getViewModel() {
        return viewModel;
    }
}