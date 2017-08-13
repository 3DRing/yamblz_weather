package tljfn.yamblzweather.utils;

import android.content.Context;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import tljfn.yamblzweather.R;

/**
 * Created by ringov on 06.08.17.
 */

public class Utils {

    public static String getRelativeTimeInPast(Context context, long time) {
        long now = System.currentTimeMillis();
        long diff = now - time;
        if (diff < TimeUnit.MINUTES.toMillis(1)) {
            return context.getString(R.string.right_now);
        } else {
            return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS).toString();
        }
    }

    public static String getFormattedTemperature(Context context, double temperature) {
        return context.getString(R.string.temperature, temperature);
    }

    public static String getFormattedDate(long time) {
        String dateFormat = "dd MMMM";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());

        return sdf.format(new Date(time));
    }
}
