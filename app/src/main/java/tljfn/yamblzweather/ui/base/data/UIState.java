package tljfn.yamblzweather.ui.base.data;

/**
 * Created by ringov on 01.08.17.
 */

public interface UIState {
    boolean hasError();

    String getErrorMessage();

    boolean isLoading();

    void setLoading(boolean loading);
}
