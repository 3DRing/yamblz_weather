package tljfn.yamblzweather.modules.base.viewmodel.lifecycle_environment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;

/**
 * Created by ringov on 07.08.17.
 */

public class TestLifecycleOwner implements LifecycleOwner {

    LifecycleRegistry registry;

    public TestLifecycleOwner() {
        registry = new LifecycleRegistry(this);
        registry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    public Lifecycle getLifecycle() {
        return registry;
    }
}
