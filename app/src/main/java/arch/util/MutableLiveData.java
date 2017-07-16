package arch.util;

import android.arch.lifecycle.LiveData;

/**
 * Created by Maksim Sukhotski on 7/16/2017.
 */

public class MutableLiveData extends LiveData {

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }
}
