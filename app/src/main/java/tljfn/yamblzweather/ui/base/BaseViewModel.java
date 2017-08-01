package tljfn.yamblzweather.ui.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import tljfn.yamblzweather.ui.base.data.UIBaseData;

/**
 * Created by ringov on 31.07.17.
 */

public abstract class BaseViewModel<D extends UIBaseData> extends ViewModel {

    protected BaseLiveData<D> liveData = new BaseLiveData<>();

    public void observe(LifecycleOwner owner, Observer<D> observer) {
        liveData.observe(owner, observer);
    }

    protected void onError(Throwable throwable) {
        liveData.changeData(buildUIError(throwable.getMessage()));
    }

    protected void onChange(D data) {
        liveData.changeData(data);
    }

    protected abstract D buildUIError(String messageError);
}
