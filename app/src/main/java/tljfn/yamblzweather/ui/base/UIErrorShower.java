package tljfn.yamblzweather.ui.base;

import android.content.Context;

import tljfn.yamblzweather.R;
import tljfn.yamblzweather.ui.base.data.UIBaseData;

/**
 * Created by ringov on 06.08.17.
 */

public class UIErrorShower<D extends UIBaseData> {

    public void showError(Context context, LoadingScreen<D> screen, D data) {
        if (data != null) {
            if (data.isLoading()) {
                screen.showLoading();
            } else {
                screen.hideLoading();
            }
            if (data.hasError()) {
                screen.onError(data.getErrorMessage());
            } else {
                screen.onSuccess(data);
            }
        } else {
            screen.onError(context.getString(R.string.no_data_loaded_error));
        }
    }
}
