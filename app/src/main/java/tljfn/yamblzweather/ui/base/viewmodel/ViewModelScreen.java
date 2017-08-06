package tljfn.yamblzweather.ui.base.viewmodel;

import tljfn.yamblzweather.ui.base.data.UIBaseData;

/**
 * Created by ringov on 06.08.17.
 */

public interface ViewModelScreen<VM extends BaseViewModel<D>, D extends UIBaseData> {

    Class<VM> getViewModelClass();

    VM getViewModel();
}
