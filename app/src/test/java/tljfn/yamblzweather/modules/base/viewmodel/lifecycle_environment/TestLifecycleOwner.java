package tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

/**
 * Created by ringov on 07.08.17.
 */

public class TestLifecycleOwner implements LifecycleOwner {
    @Override
    public Lifecycle getLifecycle() {
        return new LifecycleRegistry(this);
    }
}
