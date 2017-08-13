package tljfn.yamblzweather.modules.forecast.data;

/**
 * Created by ringov on 11.08.17.
 */

public class UIDayForecast {

    private UISingleForecast morning;
    private UISingleForecast afternoon;
    private UISingleForecast evening;
    private UISingleForecast night;

    public UISingleForecast getMorning() {
        return morning;
    }

    public UISingleForecast getAfternoon() {
        return afternoon;
    }

    public UISingleForecast getEvening() {
        return evening;
    }

    public UISingleForecast getNight() {
        return night;
    }

    public void setAfternoon(UISingleForecast afternoon) {
        this.afternoon = afternoon;
    }

    public void setEvening(UISingleForecast evening) {
        this.evening = evening;
    }

    public void setMorning(UISingleForecast morning) {
        this.morning = morning;
    }

    public void setNight(UISingleForecast night) {
        this.night = night;
    }
}
