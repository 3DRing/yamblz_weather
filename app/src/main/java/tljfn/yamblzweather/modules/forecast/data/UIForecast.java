package tljfn.yamblzweather.modules.forecast.data;

import tljfn.yamblzweather.model.db.forecast.DBForecast;
import tljfn.yamblzweather.modules.UIConverter;
import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 09.08.17.
 */

public class UIForecast extends UIBaseData {

    private static final int DEFAULT_DAYS_TO_FORECAST = 3;

    UIDayForecast[] dayForecasts;

    private long time;

    public UIDayForecast[] getDaysForecast() {
        return dayForecasts;
    }

    public long getTime() {
        return time;
    }

    public static class Builder extends UIBaseData.Builder<UIForecast, Builder> {

        public Builder() {
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

        private void checkOffset(int offset) {
            if (offset >= data.dayForecasts.length) {
                // just skip forecast if it is appears after provided daysForward value
            }
        }

        public void time(long time) {
            data.time = time;
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
