package tljfn.yamblzweather.ui.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

/**
 * Created by ringov on 31.07.17.
 */

public abstract class BaseViewModel<D extends UIBaseData> extends ViewModel {

    public abstract void observe(LifecycleOwner owner, Observer<D> observer);

}
