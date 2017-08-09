package tljfn.yamblzweather.modules.forecast;

import javax.inject.Inject;

import tljfn.yamblzweather.modules.base.viewmodel.BaseViewModel;
import tljfn.yamblzweather.modules.forecast.data.UIForecast;

/**
 * Created by ringov on 09.08.17.
 */

public class ForecastViewModel extends BaseViewModel<UIForecast> {

    private ForecastInteractor interactor;

    @Inject
    public ForecastViewModel(ForecastInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected UIForecast buildUIError(String messageError) {
        return new UIForecast.Builder().error(messageError).build();
    }
}
