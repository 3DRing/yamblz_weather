package tljfn.yamblzweather.ui.base;

import android.support.annotation.NonNull;

/**
 * Created by ringov on 06.08.17.
 */

public interface LoadingScreen<D> {
    void showLoading();

    void hideLoading();

    void onSuccess(@NonNull D data);

    void onError(String errorMessage);
}
