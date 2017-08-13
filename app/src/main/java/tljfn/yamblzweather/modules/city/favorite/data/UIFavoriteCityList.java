package tljfn.yamblzweather.modules.city.favorite.data;

import java.util.ArrayList;
import java.util.List;

import tljfn.yamblzweather.modules.base.data.UIBaseData;
import tljfn.yamblzweather.modules.city.UICity;

/**
 * Created by ringov on 08.08.17.
 */

public class UIFavoriteCityList extends UIBaseData {

    List<UICity> cities;

    private UIFavoriteCityList() {
        cities = new ArrayList<>();
    }

    public List<UICity> getFavoriteCities() {
        return cities;
    }

    public static class Builder extends UIBaseData.Builder<UIFavoriteCityList, Builder> {

        @Override
        protected UIFavoriteCityList init() {
            return new UIFavoriteCityList();
        }

        public Builder addFavoriteCity(UICity city) {
            data.cities.add(city);
            return getThis();
        }

        public Builder addAllCities(List<UICity> cities) {
            data.cities.addAll(cities);
            return getThis();
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
