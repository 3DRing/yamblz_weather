package tljfn.yamblzweather.modules.main;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 06.08.17.
 */

public class UIMainData extends UIBaseData {

    public static class Builder extends UIBaseData.Builder<UIMainData, Builder> {

        @Override
        protected UIMainData init() {
            return new UIMainData();
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
