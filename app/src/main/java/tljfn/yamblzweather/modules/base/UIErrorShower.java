package tljfn.yamblzweather.modules.base;

import android.content.Context;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 06.08.17.
 */

public class UIErrorShower<D extends UIBaseData> {

    public void showDataOrError(Context context, LoadingScreen<D> screen, D data) {
        if (data != null) {
            if (data.isLoading()) {
                screen.showLoading();
            } else {
                screen.hideLoading();
            }
            if (data.hasError()) {
                screen.onError(data.getErrorMessage());
                data.resetErrorState();
            } else {
                screen.onSuccess(data);
            }
        } else {
            screen.onError(context.getString(R.string.no_data_loaded_error));
        }
    }
}
