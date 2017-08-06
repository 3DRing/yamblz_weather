package tljfn.yamblzweather.ui.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import tljfn.yamblzweather.ui.base.data.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public abstract class BaseViewModel<D extends UIBaseData> extends ViewModel {

    private BaseLiveData<D> liveData = new BaseLiveData<>();
    private CompositeDisposable disposables;

    protected BaseViewModel() {
        disposables = new CompositeDisposable();
    }

    protected void sub(Disposable disposable) {
        disposables.add(disposable);
    }

    void observe(LifecycleOwner owner, Observer<D> observer) {
        liveData.observe(owner, observer);
    }

    protected void onError(Throwable throwable) {
        liveData.changeData(buildUIError(throwable.getMessage()));
    }

    protected void onChange(D data) {
        liveData.changeData(data);
    }

    protected abstract D buildUIError(String messageError);

    protected void showLoading() {
        D crtData = liveData.getValue();
        if (crtData != null) {
            crtData.setLoading(true);
        }
        liveData.changeData(crtData);
    }
}
