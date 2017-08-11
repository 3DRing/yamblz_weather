package tljfn.yamblzweather.modules.forecast.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 09.08.17.
 */

public class UIForecast extends UIBaseData {

    private static final int DEFAULT_DAYS_TO_FORECAST = 3;

    List<UISingleForecast> forecasts; // deprecated
    UIDayForecast[] dayForecasts;

    public UIForecast() {
        forecasts = new ArrayList<>(); // deprecated
    }

    public List<UISingleForecast> getForecasts() {
        return forecasts;
    }

    public static class Builder extends UIBaseData.Builder<UIForecast, Builder> {

        public Builder(){
            data.dayForecasts = new UIDayForecast[DEFAULT_DAYS_TO_FORECAST];
        }

        public Builder(int daysForward) {
            data.dayForecasts = new UIDayForecast[daysForward];
            for (int i = 0; i < data.dayForecasts.length; i++) {
                data.dayForecasts[i] = new UIDayForecast();
            }
        }

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

        private void checkOffset(int offset) {
            if (offset >= data.dayForecasts.length) {
                // just skip forecast if it is appears after provided daysForward value
            }
        }

        public void addMorning(int offset, DBForecast crt) {
            checkOffset(offset);
            UISingleForecast singleForecast = UIConverter.toUISingleForecast(crt);
            data.dayForecasts[offset].setMorning(singleForecast);
        }

        public void addAfternoon(int offset, DBForecast crt) {
            checkOffset(offset);
            UISingleForecast singleForecast = UIConverter.toUISingleForecast(crt);
            data.dayForecasts[offset].setAfternoon(singleForecast);
        }

        public void addEvening(int offset, DBForecast crt) {
            checkOffset(offset);
            UISingleForecast singleForecast = UIConverter.toUISingleForecast(crt);
            data.dayForecasts[offset].setEvening(singleForecast);
        }

        public void addNight(int offset, DBForecast crt) {
            checkOffset(offset);
            UISingleForecast singleForecast = UIConverter.toUISingleForecast(crt);
            data.dayForecasts[offset].setNight(singleForecast);
        }
    }

}
