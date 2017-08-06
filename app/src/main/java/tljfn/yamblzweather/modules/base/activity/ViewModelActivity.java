package tljfn.yamblzweather.modules.base.activity;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import tljfn.yamblzweather.modules.base.LoadingScreen;
import tljfn.yamblzweather.modules.base.UIErrorShower;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.base.viewmodel.ViewModelScreen;

/**
 * Created by ringov on 06.08.17.
 */

public abstract class ViewModelActivity<VM extends BaseViewModel<D>, D extends UIBaseData> extends BaseActivity
        implements ViewModelScreen<VM, D>, LoadingScreen<D>, LifecycleRegistryOwner {

    private UIErrorShower<D> errorShower;
    private VM viewModel;

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(getViewModelClass());
        viewModel.observe(this, this::onChanged);
        errorShower = new UIErrorShower<>();
    }

    @Override
    public VM getViewModel() {
        return viewModel;
    }

    protected void onChanged(@Nullable D data) {
        errorShower.showError(this, this, data);
    }
}