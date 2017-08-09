package tljfn.yamblzweather.modules.base.fragment;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import tljfn.yamblzweather.App;
import tljfn.yamblzweather.di.modules.viewmodel.ViewModelFactory;
import tljfn.yamblzweather.modules.base.LoadingScreen;
import tljfn.yamblzweather.modules.base.UIErrorShower;
import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.base.viewmodel.ViewModelScreen;

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
        inject();

        ViewModelFactory factory = getViewModelFactory();
        checkFactory(factory);
        Class<VM> cls = getViewModelClass();
        checkViewModelClass(cls);

        viewModel = ViewModelProviders.of(this, factory).get(cls);
        viewModel.observe(this, this::onChanged);
        errorShower = new UIErrorShower<>();
    }

    protected void checkViewModelClass(Class<VM> cls){
        if(cls == null){
            throw new IllegalStateException("You must override getViewModelClass and provide an instance of a view model class. Appeared in "
                    + this.getClass().getSimpleName());
        }
    }

    private void checkFactory(ViewModelFactory factory) {
        if(factory == null){
            throw new IllegalStateException("You must override getViewModelFactory and provide an instance of a factory. Appeared in "
                    + this.getClass().getSimpleName());
        }
    }

    protected abstract void inject();

    protected abstract ViewModelFactory getViewModelFactory();

    protected void onChanged(@Nullable D data) {
        errorShower.showError(getContext(), this, data);
    }

    @Override
    public VM getViewModel() {
        return viewModel;
    }
}