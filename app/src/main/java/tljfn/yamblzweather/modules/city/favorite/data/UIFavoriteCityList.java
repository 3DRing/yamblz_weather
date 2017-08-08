package tljfn.yamblzweather.modules.city.favorite.data;

import tljfn.yamblzweather.modules.base.data.UIBaseData;

/**
 * Created by ringov on 08.08.17.
 */

public class UIFavoriteCityList extends UIBaseData {

    public static class Builder extends UIBaseData.Builder<UIFavoriteCityList, Builder> {

        @Override
        protected UIFavoriteCityList init() {
            return new UIFavoriteCityList();
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
