package tljfn.yamblzweather.modules.forecast.data;

/**
 * Created by ringov on 11.08.17.
 */

public enum RelativeDay {
    BeforeYesterday(-2), Yesterday(-1), Today(0), Tomorrow(1), AfterTomorrow(2);

    private int offset;

    RelativeDay(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }
}
