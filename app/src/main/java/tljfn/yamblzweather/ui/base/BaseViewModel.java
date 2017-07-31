package tljfn.yamblzweather.ui.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import tljfn.yamblzweather.ui.start.UIWeatherData;

/**
 * Created by ringov on 31.07.17.
 */

public abstract class BaseViewModel<D extends UIBaseData> extends ViewModel {

    protected MutableLiveData<D> liveData = new MutableLiveData<>();

    public void observe(LifecycleOwner owner, Observer<D> observer) {
        liveData.observe(owner, observer);
    }

    protected void onError(Throwable throwable) {
        throw new RuntimeException(throwable.getMessage());
    }
}
