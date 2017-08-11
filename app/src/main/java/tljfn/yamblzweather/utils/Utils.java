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
    public static String getRelativeTime(Context context, long time) {
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

    public static String getFormattedTime(Context context, long time) {
        String dateFormat = "dd MMM";
        String timeFormat = " HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(context.getString(
                R.string.forecast_time_format, dateFormat, timeFormat), Locale.getDefault());

        return sdf.format(new Date(time));
    }
}
