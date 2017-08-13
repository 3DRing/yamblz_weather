package tljfn.yamblzweather;

/**
 * Created by ringov on 07.08.17.
 */

public class TestUtils {
    public static boolean equalTime(long time1, long time2) {
        return Math.abs(time1 - time2) < 1000; // diff less then 1 second
    }
}
