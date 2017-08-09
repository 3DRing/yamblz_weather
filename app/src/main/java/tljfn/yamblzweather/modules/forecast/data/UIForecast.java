package tljfn.yamblzweather.modules.forecast.data;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 09.08.17.
 */

public class UIForecast extends UIBaseData {

    UIForecast() {

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
    }

}
