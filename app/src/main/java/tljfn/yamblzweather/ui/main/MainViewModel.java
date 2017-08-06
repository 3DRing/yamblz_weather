package tljfn.yamblzweather.ui.main;

import tljfn.yamblzweather.ui.base.viewmodel.BaseViewModel;

/**
 * Created by ringov on 06.08.17.
 */

public class MainViewModel extends BaseViewModel<UIMainData> {
    @Override
    protected UIMainData buildUIError(String messageError) {
        return new UIMainData.Builder().error(messageError).build();
    }
}
