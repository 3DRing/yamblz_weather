package tljfn.yamblzweather.modules.base.viewmodel;

import tljfn.yamblzweather.data.TestUIData;

/**
 * Created by ringov on 07.08.17.
 */

public class TestViewModel extends BaseViewModel<TestUIData> {
    @Override
    protected TestUIData buildUIError(String messageError) {
        return new TestUIData.TestBuilder().error(messageError).build();
    }
}
