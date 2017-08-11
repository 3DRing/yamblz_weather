package tljfn.yamblzweather.modules.forecast.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 09.08.17.
 */

public class UIForecast extends UIBaseData {

    List<UISingleForecast> forecasts;

    public UIForecast() {
        forecasts = new ArrayList<>();
    }

    public List<UISingleForecast> getForecasts() {
        return forecasts;
    }

    public static class Builder extends UIBaseData.Builder<UIForecast, Builder> {

        @Override
        protected UIForecast init() {
            return new UIForecast();
        }

        @Override
        protected Builder getThis() {
            return this;
        }

        public Builder addSingleForecasts(List<UISingleForecast> forecasts) {
            data.forecasts.addAll(forecasts);
            return this;
        }
    }

}
