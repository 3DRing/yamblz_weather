package tljfn.yamblzweather.modules.base.viewmodel;

import android.arch.lifecycle.LiveData;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 01.08.17.
 */

public class BaseLiveData<D extends UIBaseData> extends LiveData<D> {

    public void changeData(D data) {
        this.postValue(data);
    }

}