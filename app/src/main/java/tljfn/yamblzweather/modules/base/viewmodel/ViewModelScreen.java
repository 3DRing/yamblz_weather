package tljfn.yamblzweather.modules.base.viewmodel;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 06.08.17.
 */

public interface ViewModelScreen<VM extends BaseViewModel<D>, D extends UIBaseData> {

    Class<VM> getViewModelClass();

    VM getViewModel();
}
