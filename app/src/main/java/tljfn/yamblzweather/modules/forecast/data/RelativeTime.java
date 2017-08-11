package tljfn.yamblzweather.modules.forecast.data;

import android.support.annotation.StringRes;

import tljfn.yamblzweather.R;

/**
 * Created by ringov on 11.08.17.
 */

public enum RelativeTime {
    Morning(R.string.morning), Afternoon(R.string.afternoon), Evening(R.string.evening), Night(R.string.night);

    @StringRes
    private int name;

    RelativeTime(@StringRes int name){
        this.name = name;
    }

    public int getName() {
        return name;
    }
}
