package tljfn.yamblzweather.data;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 07.08.17.
 */

public class TestUIData extends UIBaseData {
    public int field;

    public static class TestBuilder extends UIBaseData.Builder<TestUIData, TestBuilder> {

        @Override
        protected TestUIData init() {
            return new TestUIData();
        }

        @Override
        protected TestBuilder getThis() {
            return this;
        }
    }
}
